package org.project_kessel.api.auth;

import java.util.Objects;

public record ClientConfigAuth(
        String clientId,
        String clientSecret,
        String tokenEndpoint
) {
    public ClientConfigAuth {
        Objects.requireNonNull(clientId, "clientId must not be null");
        Objects.requireNonNull(clientSecret, "clientSecret must not be null");
        Objects.requireNonNull(tokenEndpoint, "tokenEndpoint must not be null");
    }
}
