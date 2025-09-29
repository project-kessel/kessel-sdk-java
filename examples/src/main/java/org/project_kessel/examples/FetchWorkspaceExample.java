package org.project_kessel.examples;

import org.project_kessel.api.auth.*;
import org.project_kessel.api.rbac.v2.*;
import org.project_kessel.examples.util.EnvConfig;

public class FetchWorkspaceExample {

    public static void main(String[] args) throws Exception {
        EnvConfig.validateRequired(
            "AUTH_DISCOVERY_ISSUER_URL",
            "AUTH_CLIENT_ID",
            "AUTH_CLIENT_SECRET",
            "RBAC_BASE_ENDPOINT"
        );

        String issuerUrl = EnvConfig.get("AUTH_DISCOVERY_ISSUER_URL");
        String clientId = EnvConfig.get("AUTH_CLIENT_ID");
        String clientSecret = EnvConfig.get("AUTH_CLIENT_SECRET");
        String rbacBase = EnvConfig.get("RBAC_BASE_ENDPOINT");

        OIDCDiscoveryMetadata discovery = OIDCDiscovery.fetchOIDCDiscovery(issuerUrl);
        OAuth2ClientCredentials oauth = new OAuth2ClientCredentials(new ClientConfigAuth(
            clientId,
            clientSecret,
            discovery.tokenEndpoint()
        ));
        OAuth2AuthRequest auth = new OAuth2AuthRequest(oauth);

        FetchWorkspaceOptions options = new FetchWorkspaceOptions(null, auth);

        Workspace def = FetchWorkspace.fetchDefaultWorkspace(rbacBase, "12345", options);
        System.out.println("Default workspace: " + def.getId());
        
        Workspace root = FetchWorkspace.fetchRootWorkspace(rbacBase, "12345", options);
        System.out.println("Root workspace: " + root.getId());
    }
}


