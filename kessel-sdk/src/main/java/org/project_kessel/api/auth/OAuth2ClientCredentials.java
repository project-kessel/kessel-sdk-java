package org.project_kessel.api.auth;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientSecretPost;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.token.AccessToken;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class OAuth2ClientCredentials {

    private static final Duration EXPIRATION_WINDOW = Duration.ofMinutes(5);
    private static final long DEFAULT_EXPIRE_IN_SECONDS = Duration.ofHours(1).toSeconds();

    private final ClientConfigAuth auth;
    private volatile RefreshTokenResponse tokenCache;
    private final ReentrantLock refreshLock = new ReentrantLock();

    public OAuth2ClientCredentials(ClientConfigAuth auth) throws OAuth2Exception {
        try {
            // Check if Nimbus OAuth library is available
            Class.forName("com.nimbusds.oauth2.sdk.TokenRequest");
            Objects.requireNonNull(auth, "auth must not be null");
            this.auth = auth;
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

        refreshLock.lock();
        try {
            // Double-check pattern - another thread might have refreshed while we waited
            if (!forceRefresh && isCacheValid()) {
                return tokenCache;
            }

            tokenCache = refresh();
            return tokenCache;
        } finally {
            refreshLock.unlock();
        }
    }

    private RefreshTokenResponse refresh() throws OAuth2Exception {
        try {
            URI tokenEndpoint = URI.create(auth.tokenEndpoint());
            ClientID clientId = new ClientID(auth.clientId());
            Secret clientSecret = new Secret(auth.clientSecret());
            ClientAuthentication clientAuth = new ClientSecretPost(clientId, clientSecret);

            ClientCredentialsGrant grant = new ClientCredentialsGrant();
            TokenRequest request = new TokenRequest(tokenEndpoint, clientAuth, grant, null);

            HTTPResponse response = request.toHTTPRequest().send();
            TokenResponse tokenResponse = TokenResponse.parse(response);

            if (!tokenResponse.indicatesSuccess()) {
                TokenErrorResponse errorResponse = tokenResponse.toErrorResponse();
                throw new OAuth2Exception("Token request failed: " + errorResponse.getErrorObject().getDescription());
            }

            AccessTokenResponse successResponse = tokenResponse.toSuccessResponse();
            AccessToken accessToken = successResponse.getTokens().getAccessToken();

            if (accessToken == null) {
                throw new OAuth2Exception("No access token received from OAuth server");
            }

            // Handle missing or invalid expires_in - default to 1 hour if not provided
            long expiresIn = successResponse.getTokens().getAccessToken().getLifetime() > 0
                ? successResponse.getTokens().getAccessToken().getLifetime()
                : DEFAULT_EXPIRE_IN_SECONDS;

            LocalDateTime expiresAt = LocalDateTime.now().plusSeconds(expiresIn);

            return new RefreshTokenResponse(accessToken.getValue(), expiresAt);

        } catch (ParseException | IOException e) {
            throw new OAuth2Exception("Failed to refresh OAuth token", e);
        }
    }
}
