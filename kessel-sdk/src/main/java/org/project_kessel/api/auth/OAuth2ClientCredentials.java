package org.project_kessel.api.auth;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretPost;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.token.AccessToken;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class OAuth2ClientCredentials {

    private static final Duration EXPIRATION_WINDOW = Duration.ofMinutes(5);
    private static final long DEFAULT_EXPIRE_IN_SECONDS = Duration.ofHours(1).toSeconds();
    private static final int HTTP_CONNECT_TIMEOUT_MS = 10_000;
    private static final int HTTP_READ_TIMEOUT_MS = 30_000;

    private final ClientConfigAuth auth;
    private final RetryOptions retryOptions;
    private volatile RefreshTokenResponse tokenCache;
    private final ReentrantLock refreshLock = new ReentrantLock();
    private final AtomicLong generation = new AtomicLong(0);

    public OAuth2ClientCredentials(ClientConfigAuth auth) throws OAuth2Exception {
        this(auth, null);
    }

    public OAuth2ClientCredentials(ClientConfigAuth auth, RetryOptions retryOptions) throws OAuth2Exception {
        try {
            // Check if Nimbus OAuth library is available
            Class.forName("com.nimbusds.oauth2.sdk.TokenRequest");
            Objects.requireNonNull(auth, "auth must not be null");
            this.auth = auth;
            this.retryOptions = retryOptions;
        } catch (ClassNotFoundException e) {
            throw new OAuth2Exception(
                "OAuth functionality requires Nimbus OAuth library. " +
                "Add dependency: com.nimbusds:oauth2-oidc-sdk"
            );
        }
    }

    public boolean isCacheValid() {
        RefreshTokenResponse token = this.tokenCache;
        if (token == null) {
            return false;
        }
        return token.expiresAt().isAfter(LocalDateTime.now().plus(EXPIRATION_WINDOW));
    }

    public RefreshTokenResponse getToken() throws OAuth2Exception {
        return getToken(false);
    }

    public RefreshTokenResponse getToken(boolean forceRefresh) throws OAuth2Exception {
        if (!forceRefresh && isCacheValid()) {
            return tokenCache;
        }

        long snapshot = generation.get();

        refreshLock.lock();
        try {
            if (generation.get() != snapshot && isCacheValid()) {
                return tokenCache;
            }

            tokenCache = refresh();
            generation.incrementAndGet();
            return tokenCache;
        } finally {
            refreshLock.unlock();
        }
    }

    private RefreshTokenResponse refresh() throws OAuth2Exception {
        int maxRetries = retryOptions != null ? retryOptions.maxRetries() : 0;

        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                URI tokenEndpoint = URI.create(auth.tokenEndpoint());
                ClientID clientId = new ClientID(auth.clientId());
                Secret clientSecret = new Secret(auth.clientSecret());
                ClientAuthentication clientAuth = new ClientSecretPost(clientId, clientSecret);

                ClientCredentialsGrant grant = new ClientCredentialsGrant();
                TokenRequest request = new TokenRequest(tokenEndpoint, clientAuth, grant, null);

                HTTPRequest httpRequest = request.toHTTPRequest();
                httpRequest.setConnectTimeout(HTTP_CONNECT_TIMEOUT_MS);
                httpRequest.setReadTimeout(HTTP_READ_TIMEOUT_MS);
                HTTPResponse httpResponse = httpRequest.send();

                // Check HTTP status for retryable errors before Nimbus parses
                // the response — Nimbus converts non-standard status codes into
                // opaque ParseExceptions, losing the original HTTP status.
                int statusCode = httpResponse.getStatusCode();
                if (isRetryableStatusCode(statusCode) && attempt < maxRetries) {
                    sleepForRetry(attempt);
                    continue;
                }

                TokenResponse tokenResponse = TokenResponse.parse(httpResponse);

                if (!tokenResponse.indicatesSuccess()) {
                    TokenErrorResponse errorResponse = tokenResponse.toErrorResponse();
                    String msg = "Token request failed: "
                        + errorResponse.getErrorObject().getDescription();
                    if (attempt > 0) {
                        msg += " (after " + (attempt + 1) + " attempts)";
                    }
                    throw new OAuth2Exception(msg);
                }

                AccessTokenResponse successResponse = tokenResponse.toSuccessResponse();
                AccessToken accessToken = successResponse.getTokens().getAccessToken();

                if (accessToken == null) {
                    throw new OAuth2Exception("No access token received from OAuth server");
                }

                // Handle missing or invalid expires_in - default to 1 hour if not provided
                long expiresIn = accessToken.getLifetime() > 0
                    ? accessToken.getLifetime()
                    : DEFAULT_EXPIRE_IN_SECONDS;

                LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(expiresIn);

                return new RefreshTokenResponse(accessToken.getValue(), expiresAt);

            } catch (IOException e) {
                if (attempt < maxRetries) {
                    sleepForRetry(attempt);
                    continue;
                }
                String msg = "Failed to refresh OAuth token";
                if (attempt > 0) {
                    msg += " after " + (attempt + 1) + " attempts";
                }
                throw new OAuth2Exception(msg, e);
            } catch (ParseException e) {
                throw new OAuth2Exception("Failed to refresh OAuth token", e);
            }
        }

        // Unreachable under normal flow, but required for compilation
        throw new OAuth2Exception("Failed to refresh OAuth token");
    }

    static boolean isRetryableStatusCode(int statusCode) {
        return statusCode == 429 || (statusCode >= 500 && statusCode <= 599);
    }

    long retryDelayMs(int retryIndex) {
        if (retryOptions == null) {
            return 0;
        }
        double cap = Math.min(retryOptions.maxDelay(),
            retryOptions.baseDelay() * Math.pow(2, retryIndex));
        if ("full".equals(retryOptions.jitter())) {
            return (long) (ThreadLocalRandom.current().nextDouble() * cap * 1000);
        }
        return (long) (cap * 1000);
    }

    private void sleepForRetry(int retryIndex) {
        long delayMs = retryDelayMs(retryIndex);
        if (delayMs > 0) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new OAuth2Exception("Token refresh retry interrupted", e);
            }
        }
    }
}
