package org.project_kessel.examples;

import java.util.Base64;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.project_kessel.api.console.Console;
import org.project_kessel.api.inventory.v1beta2.SubjectReference;

public class ConsolePrincipalExample {

    public static void main(String[] args) throws Exception {
        // --- From a parsed User identity map ---
        Map<String, Object> userIdentity = Map.of(
                "type", "User",
                "org_id", "12345",
                "user", Map.of("user_id", "7393748", "username", "jdoe"));

        SubjectReference subject = Console.principalFromRHIdentity(userIdentity);
        System.out.println("User principal:            " + subject.getResource().getResourceId());

        // --- From a parsed ServiceAccount identity map ---
        Map<String, Object> saIdentity = Map.of(
                "type", "ServiceAccount",
                "org_id", "456",
                "service_account", Map.of(
                        "user_id", "12345",
                        "client_id", "b69eaf9e-e6a6-4f9e-805e-02987daddfbd",
                        "username", "service-account-b69eaf9e"));

        subject = Console.principalFromRHIdentity(saIdentity);
        System.out.println("ServiceAccount principal:  " + subject.getResource().getResourceId());

        // --- From a raw base64-encoded x-rh-identity header ---
        Map<String, Object> headerPayload = Map.of(
                "identity", Map.of(
                        "type", "User",
                        "org_id", "12345",
                        "user", Map.of("user_id", "7393748", "username", "jdoe")));

        String header = Base64.getEncoder().encodeToString(
                new ObjectMapper().writeValueAsBytes(headerPayload));

        subject = Console.principalFromRHIdentityHeader(header);
        System.out.println("From header principal:     " + subject.getResource().getResourceId());
    }
}
