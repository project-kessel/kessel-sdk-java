package org.project_kessel.api.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OIDCDiscoveryTest {

    @Test
    void testFetchOIDCDiscoveryWithNullUrl() {
        assertThrows(Exception.class, () -> OIDCDiscovery.fetchOIDCDiscovery(null));
    }

    @Test
    void testFetchOIDCDiscoveryWithEmptyUrl() {
        assertThrows(Exception.class, () -> OIDCDiscovery.fetchOIDCDiscovery(""));
    }

    @Test
    void testFetchOIDCDiscoveryWithInvalidUrl() {
        assertThrows(OAuth2Exception.class, () -> OIDCDiscovery.fetchOIDCDiscovery("invalid-url"));
    }

    @Test
    void testFetchOIDCDiscoveryWithNonExistentHost() {
        assertThrows(OAuth2Exception.class, () -> 
            OIDCDiscovery.fetchOIDCDiscovery("https://non-existent-host-12345.com/auth/realms/test"));
    }

    @Test
    void testOIDCDiscoveryMetadataRecord() {
        // Test the record functionality
        String tokenEndpoint = "https://example.com/token";
        OIDCDiscoveryMetadata metadata = new OIDCDiscoveryMetadata(tokenEndpoint);
        
        assertEquals(tokenEndpoint, metadata.tokenEndpoint());
        assertNotNull(metadata.toString());
        assertEquals(metadata, new OIDCDiscoveryMetadata(tokenEndpoint));
        assertEquals(metadata.hashCode(), new OIDCDiscoveryMetadata(tokenEndpoint).hashCode());
    }
}