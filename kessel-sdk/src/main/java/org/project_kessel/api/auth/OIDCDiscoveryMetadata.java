package org.project_kessel.api.auth;

import java.util.Objects;

public record OIDCDiscoveryMetadata(
        String tokenEndpoint
) {

    public OIDCDiscoveryMetadata {
        Objects.requireNonNull(tokenEndpoint, "tokenEndpoint must not be null");
    }
}
