package org.project_kessel.examples;

import com.nimbusds.jose.util.Pair;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.project_kessel.api.inventory.v1beta2.*;
import org.project_kessel.examples.util.EnvConfig;

import static org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;

public class CheckForUpdateExample {

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
            CheckForUpdateRequest checkRequest = CheckForUpdateRequest
                    .newBuilder()
                    .setObject(
                            ResourceReference
                                    .newBuilder()
                                    .setReporter(ReporterReference.newBuilder().setType("rbac").build())
                                    .setResourceId("12334")
                                    .setResourceType("workspace")
                                    .build()
                    )
                    .setRelation("inventory_host_view")
                    .setSubject(
                            SubjectReference
                                    .newBuilder()
                                    .setResource(
                                            ResourceReference
                                                    .newBuilder()
                                                    .setReporter(ReporterReference.newBuilder().setType("rbac").build())
                                                    .setResourceId("foobar")
                                                    .setResourceType("principal")
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            CheckForUpdateResponse response = client.checkForUpdate(checkRequest);
            System.out.println("Check for update response received successfully:");
            System.out.println(response.toString());
        } catch (StatusRuntimeException statusException) {
            System.out.println("gRPC error occurred during Check for update:");
            statusException.printStackTrace();
        } finally {
            clientAndChannel.getRight().shutdown();
        }
    }
}
