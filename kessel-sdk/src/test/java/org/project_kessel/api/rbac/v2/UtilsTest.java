package org.project_kessel.api.rbac.v2;

import org.junit.jupiter.api.Test;
import org.project_kessel.api.inventory.v1beta2.RepresentationType;
import org.project_kessel.api.inventory.v1beta2.ResourceReference;
import org.project_kessel.api.inventory.v1beta2.SubjectReference;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void testWorkspaceType() {
        RepresentationType type = Utils.workspaceType();
        
        assertEquals("workspace", type.getResourceType());
        assertTrue(type.hasReporterType());
        assertEquals("rbac", type.getReporterType());
    }

    @Test
    void testRoleType() {
        RepresentationType type = Utils.roleType();
        
        assertEquals("role", type.getResourceType());
        assertTrue(type.hasReporterType());
        assertEquals("rbac", type.getReporterType());
    }

    @Test
    void testPrincipalResource() {
        ResourceReference resource = Utils.principalResource("user123", "redhat");
        
        assertEquals("principal", resource.getResourceType());
        assertEquals("redhat/user123", resource.getResourceId());
        assertTrue(resource.hasReporter());
        assertEquals("rbac", resource.getReporter().getType());
    }

    @Test
    void testRoleResource() {
        ResourceReference resource = Utils.roleResource("admin");
        
        assertEquals("role", resource.getResourceType());
        assertEquals("admin", resource.getResourceId());
        assertTrue(resource.hasReporter());
        assertEquals("rbac", resource.getReporter().getType());
    }

    @Test
    void testWorkspaceResource() {
        ResourceReference resource = Utils.workspaceResource("project-abc");
        
        assertEquals("workspace", resource.getResourceType());
        assertEquals("project-abc", resource.getResourceId());
        assertTrue(resource.hasReporter());
        assertEquals("rbac", resource.getReporter().getType());
    }

    @Test
    void testPrincipalSubject() {
        SubjectReference subject = Utils.principalSubject("user456", "domain");
        
        assertFalse(subject.hasRelation());
        assertTrue(subject.hasResource());
        
        ResourceReference resource = subject.getResource();
        assertEquals("principal", resource.getResourceType());
        assertEquals("domain/user456", resource.getResourceId());
        assertTrue(resource.hasReporter());
        assertEquals("rbac", resource.getReporter().getType());
    }

    @Test
    void testSubjectWithRelation() {
        ResourceReference resource = Utils.workspaceResource("ws-123");
        SubjectReference subject = Utils.subject(resource, "member");
        
        assertTrue(subject.hasRelation());
        assertEquals("member", subject.getRelation());
        assertTrue(subject.hasResource());
        assertEquals("workspace", subject.getResource().getResourceType());
        assertEquals("ws-123", subject.getResource().getResourceId());
    }

    @Test
    void testSubjectWithoutRelation() {
        ResourceReference resource = Utils.principalResource("user789", "example");
        SubjectReference subject = Utils.subject(resource, null);
        
        assertFalse(subject.hasRelation());
        assertTrue(subject.hasResource());
        assertEquals("principal", subject.getResource().getResourceType());
        assertEquals("example/user789", subject.getResource().getResourceId());
    }

    @Test
    void testSubjectWithEmptyRelation() {
        ResourceReference resource = Utils.principalResource("user999", "test");
        SubjectReference subject = Utils.subject(resource, "");
        
        assertFalse(subject.hasRelation());
        assertTrue(subject.hasResource());
        assertEquals("principal", subject.getResource().getResourceType());
    }

    @Test
    void testSubjectOverload() {
        ResourceReference resource = Utils.roleResource("viewer");
        SubjectReference subject = Utils.subject(resource);
        
        assertFalse(subject.hasRelation());
        assertTrue(subject.hasResource());
        assertEquals("role", subject.getResource().getResourceType());
        assertEquals("viewer", subject.getResource().getResourceId());
    }
}

