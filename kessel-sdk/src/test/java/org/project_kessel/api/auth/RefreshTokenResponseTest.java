package org.project_kessel.api.auth;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RefreshTokenResponseTest {

    @Test
    void testToStringRedactsAccessToken() {
        var expiresAt = LocalDateTime.of(2026, 9, 16, 12, 0, 0);
        var response = new RefreshTokenResponse("eyJhbGciOi.secret-token-value.sig", expiresAt);
        String result = response.toString();

        assertFalse(result.contains("eyJhbGciOi"), "toString must not expose accessToken");
        assertFalse(result.contains("secret-token-value"), "toString must not expose accessToken");
        assertTrue(result.contains("***"), "toString must contain redaction placeholder");
        assertTrue(result.contains("2026-09-16"), "toString must include expiresAt");
    }

    @Test
    void testToStringFormat() {
        var expiresAt = LocalDateTime.of(2026, 1, 15, 10, 30, 0);
        var response = new RefreshTokenResponse("token", expiresAt);
        assertEquals("RefreshTokenResponse[accessToken=***, expiresAt=2026-01-15T10:30]",
                response.toString());
    }

    @Test
    void testAccessorStillReturnsRealToken() {
        var expiresAt = LocalDateTime.of(2026, 9, 16, 12, 0, 0);
        var response = new RefreshTokenResponse("real-token", expiresAt);

        assertEquals("real-token", response.accessToken());
        assertEquals(expiresAt, response.expiresAt());
    }
}
