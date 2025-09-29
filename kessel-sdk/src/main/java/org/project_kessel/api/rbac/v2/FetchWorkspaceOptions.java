package org.project_kessel.api.rbac.v2;

import org.project_kessel.api.auth.AuthRequest;

import java.net.http.HttpClient;

public record FetchWorkspaceOptions(
        HttpClient httpClient,
        AuthRequest auth) {
}
