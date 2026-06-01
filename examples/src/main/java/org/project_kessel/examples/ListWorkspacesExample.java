package org.project_kessel.examples;

import com.nimbusds.jose.util.Pair;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;
import org.project_kessel.api.inventory.v1beta2.StreamedListObjectsResponse;
import org.project_kessel.api.inventory.v1beta2.ClientBuilder;
import org.project_kessel.api.rbac.v2.ListWorkspaces;
import org.project_kessel.api.rbac.v2.Utils;
import org.project_kessel.examples.util.EnvConfig;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ListWorkspacesExample {

    public static void main(String[] args) {
        // Validate required environment variables
        EnvConfig.validateRequired(
            "KESSEL_ENDPOINT"
        );

        // Load configuration from environment/.env file
        String kesselEndpoint = EnvConfig.get("KESSEL_ENDPOINT");

        Pair<KesselInventoryServiceBlockingStub, ManagedChannel> clientAndChannel = new ClientBuilder(kesselEndpoint)
                .insecure()
                .build();

        KesselInventoryServiceBlockingStub client = clientAndChannel.getLeft();

        try {
            Iterable<StreamedListObjectsResponse> workspaces = ListWorkspaces.listWorkspaces(
                    client,
                    Utils.principalSubject("alice", "redhat"),
                    "view_document");

            // Iterate one-by-one (lazy, constant memory)
            System.out.println("Listing all workspaces:");
            for (StreamedListObjectsResponse response : workspaces) {
                System.out.println("Found workspace: " + response.getObject().getResourceId());
            }

            // Materialise all workspaces into a List
            List<StreamedListObjectsResponse> allWorkspaces =
                StreamSupport.stream(workspaces.spliterator(), false)
                    .collect(Collectors.toList());
            System.out.println("Total workspaces: " + allWorkspaces.size());
        } catch (StatusRuntimeException statusException) {
            System.err.println("gRPC error occurred while listing workspaces:");
            statusException.printStackTrace();
        } finally {
            clientAndChannel.getRight().shutdown();
        }
    }
}
