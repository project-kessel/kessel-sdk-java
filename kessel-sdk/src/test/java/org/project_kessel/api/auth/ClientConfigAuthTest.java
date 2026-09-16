package org.project_kessel.api.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientConfigAuthTest {

    @Test
    void testToStringRedactsClientSecret() {
        var config = new ClientConfigAuth("my-client", "super-secret-value", "https://sso.example.com/token");
        String result = config.toString();

        assertFalse(result.contains("super-secret-value"), "toString must not expose clientSecret");
        assertTrue(result.contains("***"), "toString must contain redaction placeholder");
        assertTrue(result.contains("my-client"), "toString must include clientId");
        assertTrue(result.contains("https://sso.example.com/token"), "toString must include tokenEndpoint");
    }

    @Test
    void testToStringFormat() {
        var config = new ClientConfigAuth("cid", "secret", "https://endpoint");
        assertEquals("ClientConfigAuth[clientId=cid, clientSecret=***, tokenEndpoint=https://endpoint]",
                config.toString());
    }

    @Test
    void testAccessorsStillReturnRealValues() {
        var config = new ClientConfigAuth("cid", "real-secret", "https://endpoint");

        assertEquals("cid", config.clientId());
        assertEquals("real-secret", config.clientSecret());
        assertEquals("https://endpoint", config.tokenEndpoint());
    }
}
