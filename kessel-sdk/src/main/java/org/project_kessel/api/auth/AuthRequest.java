package org.project_kessel.api.auth;

import java.net.http.HttpRequest;

/**
 * Abstraction for configuring HTTP requests with authentication.
 */
public interface AuthRequest {

    /**
     * Configure the provided HTTP request builder.
     *
     * @param requestBuilder the request builder to configure
     * @throws OAuth2Exception when token acquisition or configuration fails
     */
    void configureRequest(HttpRequest.Builder requestBuilder) throws OAuth2Exception;
}


