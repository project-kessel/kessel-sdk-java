package org.project_kessel.api.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OAuth2ClientCredentialsTest {

    private ClientConfigAuth config;

    @BeforeEach
    void setUp() {
        config = new ClientConfigAuth("test-client", "test-secret", "https://example.com/token");
    }

    @Test
    void testConstructorWithValidConfig() {
        assertDoesNotThrow(() -> new OAuth2ClientCredentials(config));
    }

    @Test
    void testConstructorWithNullConfig() {
        // The constructor now validates for null config with Objects.requireNonNull
        assertThrows(NullPointerException.class, () -> new OAuth2ClientCredentials(null));
    }

    @Test
    void testConstructorWithNimbusLibrary() {
        // This test verifies that the class properly handles Nimbus availability
        // Since Nimbus is available in our test environment, the constructor should succeed
        assertDoesNotThrow(() -> new OAuth2ClientCredentials(config));
    }

    @Test
    void testClientConfigAuthProperties() {
        assertEquals("test-client", config.clientId());
        assertEquals("test-secret", config.clientSecret());
        assertEquals("https://example.com/token", config.tokenEndpoint());
    }

    @Test
    void testClientConfigAuthValidatesNullValues() {
        // Now the record validates null values with Objects.requireNonNull
        assertThrows(NullPointerException.class, () -> new ClientConfigAuth(null, "secret", "endpoint"));
        assertThrows(NullPointerException.class, () -> new ClientConfigAuth("client", null, "endpoint"));
        assertThrows(NullPointerException.class, () -> new ClientConfigAuth("client", "secret", null));
    }
}