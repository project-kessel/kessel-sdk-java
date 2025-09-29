package org.project_kessel.api.rbac.v2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.project_kessel.api.auth.AuthRequest;
import org.project_kessel.api.auth.OAuth2Exception;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Workspaces {

    private static final String WORKSPACE_ENDPOINT = "/api/rbac/v2/workspaces/";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Workspace fetchRootWorkspace(String rbacBaseEndpoint, String orgId, FetchWorkspaceOptions options)
            throws IOException, InterruptedException, OAuth2Exception {
        return fetchWorkspace(rbacBaseEndpoint, orgId, "root", options);
    }

    public static Workspace fetchDefaultWorkspace(String rbacBaseEndpoint, String orgId, FetchWorkspaceOptions options)
            throws IOException, InterruptedException, OAuth2Exception {
        return fetchWorkspace(rbacBaseEndpoint, orgId, "default", options);
    }

    private static Workspace fetchWorkspace(String rbacBaseEndpoint, String orgId, String type,
            FetchWorkspaceOptions options) throws IOException, InterruptedException, OAuth2Exception {
        HttpClient httpClient = options.httpClient();
        if (httpClient == null) {
            httpClient = HttpClient.newHttpClient();
        }

        if (rbacBaseEndpoint.endsWith("/")) {
            rbacBaseEndpoint = rbacBaseEndpoint.substring(0, rbacBaseEndpoint.length() - 1);
        }
        String url = rbacBaseEndpoint + WORKSPACE_ENDPOINT + "?type=" + type;

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rh-rbac-org-id", orgId)
                .GET();

        AuthRequest auth = options.auth();
        if (auth != null) {
            auth.configureRequest(requestBuilder);
        }

        HttpResponse<byte[]> response = httpClient.send(requestBuilder.build(),
                HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() != 200) {
            throw new IOException("error fetching " + type + " workspace - http status " + response.statusCode());
        }

        JsonNode root = MAPPER.readTree(response.body());
        JsonNode data = root.get("data");
        if (data.size() != 1) {
            throw new IOException("unexpected number of " + type + " workspaces");
        }
        JsonNode obj = data.get(0);
        Workspace ws = MAPPER.readValue(obj.toString(), Workspace.class);
        return ws;
    }
}
