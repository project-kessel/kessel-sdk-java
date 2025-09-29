package org.project_kessel.api.auth;

import java.net.http.HttpRequest;

/**
 * Wraps OAuth2ClientCredentials to inject a Bearer token into HTTP requests.
 */
public class OAuth2AuthRequest implements AuthRequest {

    private OAuth2ClientCredentials credentials;

    public OAuth2AuthRequest(OAuth2ClientCredentials credentials) {
        this.credentials = credentials;
    }

    public void configureRequest(HttpRequest.Builder requestBuilder) throws OAuth2Exception {
        RefreshTokenResponse token = credentials.getToken();
        requestBuilder.header("authorization", "Bearer " + token.accessToken());
    }
}


