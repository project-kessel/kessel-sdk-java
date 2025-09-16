package org.project_kessel.api.auth;

import java.time.LocalDateTime;
import java.util.Objects;

public record RefreshTokenResponse(
    String accessToken,
    LocalDateTime expiresAt
) {

    public RefreshTokenResponse {
        Objects.requireNonNull(accessToken, "accessToken must not be null");
        Objects.requireNonNull(expiresAt, "expiresAt must not be null");
    }
}
