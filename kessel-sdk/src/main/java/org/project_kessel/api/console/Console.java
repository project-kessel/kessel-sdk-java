package org.project_kessel.api.console;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.project_kessel.api.inventory.v1beta2.SubjectReference;
import org.project_kessel.api.rbac.v2.Utils;

public class Console {

    private static final String DEFAULT_DOMAIN = "redhat";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final List<String> SUPPORTED_TYPES = List.of("User", "ServiceAccount");

    private Console() {
    }

    static String extractUserID(Map<String, Object> identity) {
        if (identity == null) {
            throw new IllegalArgumentException("identity must not be null");
        }

        Object typeObj = identity.get("type");
        String type = typeObj instanceof String ? (String) typeObj : null;
        String field;

        if ("User".equals(type)) {
            field = "user";
        } else if ("ServiceAccount".equals(type)) {
            field = "service_account";
        } else {
            throw new IllegalArgumentException(
                    "Unsupported identity type: \"" + typeObj + "\" (supported: " + SUPPORTED_TYPES + ")");
        }

        Object details = identity.get(field);
        if (!(details instanceof Map)) {
            throw new IllegalArgumentException(
                    "Identity type \"" + type + "\" is missing the \"" + field + "\" field");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> detailsMap = (Map<String, Object>) details;
        Object userIdObj = detailsMap.get("user_id");
        String userId = userIdObj != null ? userIdObj.toString() : null;

        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException(
                    "Unable to resolve user ID from " + type + " identity (tried: user_id)");
        }

        return userId;
    }

    public static SubjectReference principalFromRHIdentity(Map<String, Object> identity, String domain) {
        if (domain == null || domain.trim().isEmpty()) {
            throw new IllegalArgumentException("domain must not be null or blank");
        }
        String userId = extractUserID(identity);
        return Utils.principalSubject(userId, domain);
    }

    public static SubjectReference principalFromRHIdentity(Map<String, Object> identity) {
        return principalFromRHIdentity(identity, DEFAULT_DOMAIN);
    }

    public static SubjectReference principalFromRHIdentityHeader(String header, String domain) {
        Map<String, Object> decoded;
        try {
            byte[] bytes = Base64.getDecoder().decode(header);
            decoded = OBJECT_MAPPER.readValue(
                    new String(bytes, StandardCharsets.UTF_8),
                    new TypeReference<Map<String, Object>>() {
                    });
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to decode identity header: " + e.getMessage(), e);
        }

        Object identityObj = decoded.get("identity");
        if (identityObj == null) {
            throw new IllegalArgumentException("Identity header is missing the \"identity\" envelope key");
        }

        if (!(identityObj instanceof Map)) {
            throw new IllegalArgumentException("Identity header did not decode to a JSON object");
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> identity = (Map<String, Object>) identityObj;

        return principalFromRHIdentity(identity, domain);
    }

    public static SubjectReference principalFromRHIdentityHeader(String header) {
        return principalFromRHIdentityHeader(header, DEFAULT_DOMAIN);
    }
}
