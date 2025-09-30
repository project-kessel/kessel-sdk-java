package org.project_kessel.api.rbac.v2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project_kessel.api.auth.AuthRequest;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class FetchWorkspaceTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<byte[]> mockResponse;

    @Mock
    private AuthRequest mockAuth;

    private static final String BASE_ENDPOINT = "https://rbac.example.com";
    private static final String ORG_ID = "12345";

    private static final String VALID_ROOT_WORKSPACE_JSON = """
            {
                "data": [
                    {
                        "id": "root-ws-1",
                        "name": "Root Workspace",
                        "type": "root",
                        "description": "The root workspace"
                    }
                ]
            }
            """;

    private static final String VALID_DEFAULT_WORKSPACE_JSON = """
            {
                "data": [
                    {
                        "id": "default-ws-1",
                        "name": "Default Workspace",
                        "type": "default",
                        "description": "The default workspace"
                    }
                ]
            }
            """;

    private static final String EMPTY_DATA_JSON = """
            {
                "data": []
            }
            """;

    private static final String MULTIPLE_WORKSPACES_JSON = """
            {
                "data": [
                    {
                        "id": "ws-1",
                        "name": "Workspace 1",
                        "type": "root",
                        "description": "The root workspace"
                    },
                    {
                        "id": "ws-2",
                        "name": "Workspace 2",
                        "type": "root",
                        "description": "Also the root workspace"
                    }
                ]
            }
            """;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        lenient().when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);
    }

    @Test
    void testWorkspaceGetters() {
        Workspace workspace = new Workspace();

        // Initially null
        assertNull(workspace.getId());
        assertNull(workspace.getName());
        assertNull(workspace.getType());
        assertNull(workspace.getDescription());
    }

    @Test
    void testFetchRootWorkspaceWithNullEndpoint() {
        assertThrows(NullPointerException.class, () -> FetchWorkspace.fetchRootWorkspace(null, "orgId"));
    }

    @Test
    void testFetchDefaultWorkspaceWithNullEndpoint() {
        assertThrows(NullPointerException.class, () -> FetchWorkspace.fetchDefaultWorkspace(null, "orgId"));
    }

    @Test
    void testFetchRootWorkspaceWithNullOrgId() {
        assertThrows(NullPointerException.class,
                () -> FetchWorkspace.fetchRootWorkspace("https://example.com", null));
    }

    @Test
    void testFetchDefaultWorkspaceWithNullOrgId() {
        assertThrows(NullPointerException.class,
                () -> FetchWorkspace.fetchDefaultWorkspace("https://example.com", null));
    }

    @Test
    void testFetchRootWorkspaceWithInvalidEndpoint() {
        assertThrows(IOException.class,
                () -> FetchWorkspace.fetchRootWorkspace("https://non-existent-host-12345.com", "orgId"));
    }

    @Test
    void testFetchDefaultWorkspaceWithInvalidEndpoint() {
        assertThrows(IOException.class,
                () -> FetchWorkspace.fetchDefaultWorkspace("https://non-existent-host-12345.com", "orgId"));
    }

    @Test
    void testFetchRootWorkspaceSuccess() throws Exception {
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(VALID_ROOT_WORKSPACE_JSON.getBytes());

        Workspace workspace = FetchWorkspace.fetchRootWorkspace(BASE_ENDPOINT, ORG_ID, mockAuth, mockHttpClient);

        assertNotNull(workspace);
        assertEquals("root-ws-1", workspace.getId());
        assertEquals("Root Workspace", workspace.getName());
        assertEquals("root", workspace.getType());
        assertEquals("The root workspace", workspace.getDescription());

        verify(mockAuth).configureRequest(any(HttpRequest.Builder.class));
    }

    @Test
    void testFetchDefaultWorkspaceSuccess() throws Exception {
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(VALID_DEFAULT_WORKSPACE_JSON.getBytes());

        Workspace workspace = FetchWorkspace.fetchDefaultWorkspace(BASE_ENDPOINT, ORG_ID, mockAuth, mockHttpClient);

        assertNotNull(workspace);
        assertEquals("default-ws-1", workspace.getId());
        assertEquals("Default Workspace", workspace.getName());
        assertEquals("default", workspace.getType());
        assertEquals("The default workspace", workspace.getDescription());

        verify(mockAuth).configureRequest(any(HttpRequest.Builder.class));
    }

    @Test
    void testFetchWorkspaceWithoutAuth() throws Exception {
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(VALID_ROOT_WORKSPACE_JSON.getBytes());

        Workspace workspace = FetchWorkspace.fetchRootWorkspace(BASE_ENDPOINT, ORG_ID, null, mockHttpClient);

        assertNotNull(workspace);
        assertEquals("root-ws-1", workspace.getId());

        verifyNoInteractions(mockAuth);
    }

    @Test
    void testFetchWorkspaceEmptyData() {
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(EMPTY_DATA_JSON.getBytes());

        IOException exception = assertThrows(IOException.class,
                () -> FetchWorkspace.fetchRootWorkspace(BASE_ENDPOINT, ORG_ID, mockAuth, mockHttpClient));

        assertTrue(exception.getMessage().contains("unexpected number of root workspaces"));
    }

    @Test
    void testFetchWorkspaceMultipleWorkspaces() {
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(MULTIPLE_WORKSPACES_JSON.getBytes());

        IOException exception = assertThrows(IOException.class,
                () -> FetchWorkspace.fetchRootWorkspace(BASE_ENDPOINT, ORG_ID, mockAuth, mockHttpClient));

        assertTrue(exception.getMessage().contains("unexpected number of root workspaces"));
    }
}