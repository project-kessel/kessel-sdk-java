package org.project_kessel.api.rbac.v2;

import org.project_kessel.api.inventory.v1beta2.RepresentationType;
import org.project_kessel.api.inventory.v1beta2.ReporterReference;
import org.project_kessel.api.inventory.v1beta2.ResourceReference;
import org.project_kessel.api.inventory.v1beta2.SubjectReference;

public class Utils {

    private static final String REPORTER_TYPE = "rbac";
    private static final String WORKSPACE_TYPE = "workspace";
    private static final String ROLE_TYPE = "role";
    private static final String PRINCIPAL_TYPE = "principal";

    public static RepresentationType workspaceType() {
        return RepresentationType.newBuilder()
                .setResourceType(WORKSPACE_TYPE)
                .setReporterType(REPORTER_TYPE)
                .build();
    }

    public static RepresentationType roleType() {
        return RepresentationType.newBuilder()
                .setResourceType(ROLE_TYPE)
                .setReporterType(REPORTER_TYPE)
                .build();
    }

    public static ResourceReference principalResource(String id, String domain) {
        return ResourceReference.newBuilder()
                .setResourceType(PRINCIPAL_TYPE)
                .setResourceId(domain + "/" + id)
                .setReporter(ReporterReference.newBuilder()
                        .setType(REPORTER_TYPE)
                        .build())
                .build();
    }

    public static ResourceReference roleResource(String resourceId) {
        return ResourceReference.newBuilder()
                .setResourceType(ROLE_TYPE)
                .setResourceId(resourceId)
                .setReporter(ReporterReference.newBuilder()
                        .setType(REPORTER_TYPE)
                        .build())
                .build();
    }

    public static ResourceReference workspaceResource(String resourceId) {
        return ResourceReference.newBuilder()
                .setResourceType(WORKSPACE_TYPE)
                .setResourceId(resourceId)
                .setReporter(ReporterReference.newBuilder()
                        .setType(REPORTER_TYPE)
                        .build())
                .build();
    }

    public static SubjectReference principalSubject(String id, String domain) {
        return SubjectReference.newBuilder()
                .setResource(principalResource(id, domain))
                .build();
    }

    public static SubjectReference subject(ResourceReference resourceRef, String relation) {
        SubjectReference.Builder builder = SubjectReference.newBuilder()
                .setResource(resourceRef);

        if (relation != null && !relation.isEmpty()) {
            builder.setRelation(relation);
        }

        return builder.build();
    }

    public static SubjectReference subject(ResourceReference resourceRef) {
        return subject(resourceRef, null);
    }
}
