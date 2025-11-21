package org.project_kessel.examples;

import com.nimbusds.jose.util.Pair;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.project_kessel.api.inventory.v1beta2.*;
import org.project_kessel.api.rbac.v2.Utils;
import org.project_kessel.examples.util.EnvConfig;

import static org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;

public class CheckBulkExample {

    public static void main(String[] args) {
        // Validate required environment variables
        EnvConfig.validateRequired(
                "KESSEL_ENDPOINT");

        // Load configuration from environment/.env file
        String kesselEndpoint = EnvConfig.get("KESSEL_ENDPOINT");

        Pair<KesselInventoryServiceBlockingStub, ManagedChannel> clientAndChannel = new ClientBuilder(kesselEndpoint)
                .insecure()
                .build();
        KesselInventoryServiceBlockingStub client = clientAndChannel.getLeft();

        try {
            // Item 1: Check if bob can view widgets in workspace_123
            CheckBulkRequestItem item1 = CheckBulkRequestItem
                    .newBuilder()
                    .setObject(Utils.workspaceResource("workspace_123"))
                    .setRelation("view_widget")
                    .setSubject(Utils.principalSubject("bob", "redhat"))
                    .build();

            // Item 2: Check if bob can use widgets in workspace_456
            CheckBulkRequestItem item2 = CheckBulkRequestItem
                    .newBuilder()
                    .setObject(Utils.workspaceResource("workspace_456"))
                    .setRelation("use_widget")
                    .setSubject(Utils.principalSubject("bob", "redhat"))
                    .build();

            // Item 3: Check with invalid resource type to demonstrate error handling
            CheckBulkRequestItem item3 = CheckBulkRequestItem
                    .newBuilder()
                    .setObject(
                            ResourceReference
                                    .newBuilder()
                                    .setReporter(ReporterReference.newBuilder().setType("rbac").build())
                                    .setResourceId("invalid_resource")
                                    .setResourceType("not_a_valid_type")
                                    .build())
                    .setRelation("view_widget")
                    .setSubject(Utils.principalSubject("alice", "redhat"))
                    .build();

            CheckBulkRequest checkBulkRequest = CheckBulkRequest
                    .newBuilder()
                    .addItems(item1)
                    .addItems(item2)
                    .addItems(item3)
                    .build();

            CheckBulkResponse response = client.checkBulk(checkBulkRequest);
            System.out.println("CheckBulk response received successfully");
            System.out.println("Total pairs in response: " + response.getPairsCount());
            System.out.println();

            for (int idx = 0; idx < response.getPairsCount(); idx++) {
                CheckBulkResponsePair pair = response.getPairs(idx);
                System.out.println("--- Result " + (idx + 1) + " ---");

                CheckBulkRequestItem req = pair.getRequest();
                System.out.println("Request: subject=" + req.getSubject().getResource().getResourceId() +
                        " relation=" + req.getRelation() +
                        " object=" + req.getObject().getResourceId());

                if (pair.hasItem()) {
                    CheckBulkResponseItem item = pair.getItem();
                    System.out.println(item.toString());
                } else if (pair.hasError()) {
                    com.google.rpc.Status error = pair.getError();
                    System.out.println("Error: Code=" + error.getCode() + ", Message=" + error.getMessage());
                }
            }
        } catch (StatusRuntimeException statusException) {
            System.out.println("gRPC error occurred during CheckBulk:");
            statusException.printStackTrace();
        } finally {
            clientAndChannel.getRight().shutdown();
        }
    }
}
