package org.project_kessel.api.console;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.project_kessel.api.inventory.v1beta2.SubjectReference;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    @Nested
    class ExtractUserIDTest {
        @Test
        void testUserWithUserId() {
            Map<String, Object> identity = Map.of(
                    "type", "User",
                    "user", Map.of("user_id", "7393748", "username", "foobar"));
            assertEquals("7393748", Console.extractUserID(identity));
        }

        @Test
        void testUserMissingUserId() {
            Map<String, Object> identity = Map.of(
                    "type", "User",
                    "user", Map.of("username", "foobar"));
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.extractUserID(identity));
            assertTrue(ex.getMessage().contains("Unable to resolve user ID"));
        }

        @Test
        void testUserEmptyUserId() {
            Map<String, Object> identity = Map.of(
                    "type", "User",
                    "user", Map.of("user_id", ""));
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.extractUserID(identity));
            assertTrue(ex.getMessage().contains("Unable to resolve user ID"));
        }

        @Test
        void testServiceAccountWithUserId() {
            Map<String, Object> identity = Map.of(
                    "type", "ServiceAccount",
                    "service_account", Map.of("user_id", "sa-456", "client_id", "b69eaf9e"));
            assertEquals("sa-456", Console.extractUserID(identity));
        }

        @Test
        void testServiceAccountMissingUserId() {
            Map<String, Object> identity = Map.of(
                    "type", "ServiceAccount",
                    "service_account", Map.of("client_id", "b69eaf9e"));
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.extractUserID(identity));
            assertTrue(ex.getMessage().contains("Unable to resolve user ID"));
        }

        @ParameterizedTest
        @ValueSource(strings = { "System", "X509", "Associate" })
        void testUnsupportedIdentityType(String type) {
            Map<String, Object> identity = Map.of("type", type);
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.extractUserID(identity));
            assertTrue(ex.getMessage().contains("Unsupported identity type"));
        }

        @Test
        void testMissingTypeField() {
            Map<String, Object> identity = Map.of("org_id", "123");
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.extractUserID(identity));
            assertTrue(ex.getMessage().contains("Unsupported identity type"));
        }

        @Test
        void testMissingUserDetails() {
            Map<String, Object> identity = Map.of("type", "User");
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.extractUserID(identity));
            assertTrue(ex.getMessage().contains("missing the \"user\" field"));
        }

        @Test
        void testMissingServiceAccountDetails() {
            Map<String, Object> identity = Map.of("type", "ServiceAccount");
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.extractUserID(identity));
            assertTrue(ex.getMessage().contains("missing the \"service_account\" field"));
        }

        @Test
        void testUserDetailsNotAMap() {
            Map<String, Object> identity = Map.of("type", "User", "user", "not-a-map");
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.extractUserID(identity));
            assertTrue(ex.getMessage().contains("missing the \"user\" field"));
        }

        @Test
        void testNullIdentity() {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.extractUserID(null));
            assertTrue(ex.getMessage().contains("identity must not be null"));
        }
    }

    @Nested
    class PrincipalFromRHIdentityTest {
        @Test
        void testUserIdentity() {
            Map<String, Object> identity = Map.of(
                    "type", "User",
                    "org_id", "1979710",
                    "user", Map.of("user_id", "7393748", "username", "foobar"));
            SubjectReference ref = Console.principalFromRHIdentity(identity);
            assertEquals("principal", ref.getResource().getResourceType());
            assertEquals("redhat/7393748", ref.getResource().getResourceId());
            assertEquals("rbac", ref.getResource().getReporter().getType());
        }

        @Test
        void testServiceAccountIdentity() {
            Map<String, Object> identity = Map.of(
                    "type", "ServiceAccount",
                    "org_id", "456",
                    "service_account",
                    Map.of("user_id", "sa-456", "client_id", "b69eaf9e", "username", "svc-b69eaf9e"));
            SubjectReference ref = Console.principalFromRHIdentity(identity);
            assertEquals("redhat/sa-456", ref.getResource().getResourceId());
        }

        @Test
        void testCustomDomain() {
            Map<String, Object> identity = Map.of(
                    "type", "User",
                    "user", Map.of("user_id", "42"));
            SubjectReference ref = Console.principalFromRHIdentity(identity, "custom");
            assertEquals("custom/42", ref.getResource().getResourceId());
        }

        @Test
        void testUnsupportedTypePropagate() {
            Map<String, Object> identity = Map.of("type", "System");
            assertThrows(IllegalArgumentException.class,
                    () -> Console.principalFromRHIdentity(identity));
        }
    }

    private String encodeHeader(Map<String, Object> payload) {
        try {
            String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(payload);
            return Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Nested
    class PrincipalFromRHIdentityHeaderTest {
        @Test
        void testFullEnvelope() {
            String header = encodeHeader(Map.of(
                    "identity", Map.of(
                            "type", "User",
                            "org_id", "1979710",
                            "user", Map.of("user_id", "7393748", "username", "foobar"))));
            SubjectReference ref = Console.principalFromRHIdentityHeader(header);
            assertEquals("redhat/7393748", ref.getResource().getResourceId());
            assertEquals("principal", ref.getResource().getResourceType());
        }

        @Test
        void testMissingIdentityEnvelope() {
            String header = encodeHeader(Map.of(
                    "type", "User",
                    "user", Map.of("user_id", "42")));
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.principalFromRHIdentityHeader(header));
            assertTrue(ex.getMessage().contains("missing the \"identity\" envelope key"));
        }

        @Test
        void testServiceAccountHeader() {
            String header = encodeHeader(Map.of(
                    "identity", Map.of(
                            "type", "ServiceAccount",
                            "org_id", "456",
                            "service_account", Map.of("user_id", "sa-789", "client_id", "b69eaf9e"))));
            SubjectReference ref = Console.principalFromRHIdentityHeader(header);
            assertEquals("redhat/sa-789", ref.getResource().getResourceId());
        }

        @Test
        void testCustomDomain() {
            String header = encodeHeader(Map.of(
                    "identity", Map.of(
                            "type", "User",
                            "user", Map.of("user_id", "1"))));
            SubjectReference ref = Console.principalFromRHIdentityHeader(header, "acme");
            assertEquals("acme/1", ref.getResource().getResourceId());
        }

        @Test
        void testMalformedBase64() {
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.principalFromRHIdentityHeader("not-valid-base64!!!"));
            assertTrue(ex.getMessage().contains("Failed to decode identity header"));
        }

        @Test
        void testInvalidJson() {
            String header = Base64.getEncoder().encodeToString("this is not json".getBytes(StandardCharsets.UTF_8));
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.principalFromRHIdentityHeader(header));
            assertTrue(ex.getMessage().contains("Failed to decode identity header"));
        }

        @Test
        void testUnsupportedTypeInHeader() {
            String header = encodeHeader(Map.of(
                    "identity", Map.of("type", "System")));
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                    () -> Console.principalFromRHIdentityHeader(header));
            assertTrue(ex.getMessage().contains("Unsupported identity type"));
        }

        @Test
        void testRealisticUserHeader() {
            Map<String, Object> user = new HashMap<>();
            user.put("username", "rhn-support-foobar");
            user.put("is_internal", true);
            user.put("is_org_admin", true);
            user.put("first_name", "foo");
            user.put("last_name", "bar");
            user.put("is_active", true);
            user.put("user_id", "7393748");
            user.put("email", "example@redhat.com");

            Map<String, Object> identityInner = new HashMap<>();
            identityInner.put("account_number", "540155");
            identityInner.put("org_id", "1979710");
            identityInner.put("user", user);
            identityInner.put("type", "User");

            String header = encodeHeader(Map.of("identity", identityInner));
            SubjectReference ref = Console.principalFromRHIdentityHeader(header);
            assertEquals("redhat/7393748", ref.getResource().getResourceId());
            assertEquals("principal", ref.getResource().getResourceType());
            assertEquals("rbac", ref.getResource().getReporter().getType());
        }

        @Test
        void testRealisticServiceAccountHeader() {
            String header = encodeHeader(Map.of(
                    "identity", Map.of(
                            "org_id", "456",
                            "type", "ServiceAccount",
                            "service_account", Map.of(
                                    "user_id", "sa-b69eaf9e",
                                    "client_id", "b69eaf9e-e6a6-4f9e-805e-02987daddfbd",
                                    "username", "service-account-b69eaf9e-e6a6-4f9e-805e-02987daddfbd"))));
            SubjectReference ref = Console.principalFromRHIdentityHeader(header);
            assertEquals("redhat/sa-b69eaf9e", ref.getResource().getResourceId());
        }
    }
}
