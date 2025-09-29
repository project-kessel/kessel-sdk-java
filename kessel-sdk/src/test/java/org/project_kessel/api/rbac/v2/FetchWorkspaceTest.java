package org.project_kessel.api.rbac.v2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FetchWorkspaceTest {

    @Test
    void testFetchWorkspaceOptions() {
        FetchWorkspaceOptions options = new FetchWorkspaceOptions(null, null);
        
        assertNull(options.httpClient());
        assertNull(options.auth());
        assertNotNull(options.toString());
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
        assertThrows(Exception.class, () -> 
            FetchWorkspace.fetchRootWorkspace(null, "orgId", null));
    }

    @Test
    void testFetchDefaultWorkspaceWithNullEndpoint() {
        assertThrows(Exception.class, () -> 
            FetchWorkspace.fetchDefaultWorkspace(null, "orgId", null));
    }

    @Test
    void testFetchRootWorkspaceWithNullOrgId() {
        assertThrows(Exception.class, () -> 
            FetchWorkspace.fetchRootWorkspace("https://example.com", null, null));
    }

    @Test
    void testFetchDefaultWorkspaceWithNullOrgId() {
        assertThrows(Exception.class, () -> 
            FetchWorkspace.fetchDefaultWorkspace("https://example.com", null, null));
    }

    @Test
    void testFetchRootWorkspaceWithInvalidEndpoint() {
        FetchWorkspaceOptions options = new FetchWorkspaceOptions(null, null);
        assertThrows(Exception.class, () -> 
            FetchWorkspace.fetchRootWorkspace("https://non-existent-host-12345.com", "orgId", options));
    }

    @Test
    void testFetchDefaultWorkspaceWithInvalidEndpoint() {
        FetchWorkspaceOptions options = new FetchWorkspaceOptions(null, null);
        assertThrows(Exception.class, () -> 
            FetchWorkspace.fetchDefaultWorkspace("https://non-existent-host-12345.com", "orgId", options));
    }
}