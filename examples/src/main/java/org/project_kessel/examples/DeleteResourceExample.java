package org.project_kessel.examples;

import com.nimbusds.jose.util.Pair;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.project_kessel.api.inventory.v1beta2.*;
import org.project_kessel.examples.util.EnvConfig;

import static org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;

public class DeleteResourceExample {

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
            DeleteResourceRequest deleteResourceRequest = DeleteResourceRequest
                    .newBuilder()
                    .setReference(
                            ResourceReference
                                    .newBuilder()
                                    .setReporter(ReporterReference.newBuilder().setType("hbi").build())
                                    .setResourceId("854589f0-3be7-4cad-8bcd-45e18f33cb81")
                                    .setResourceType("host")
                                    .build()
                    )
                    .build();

            DeleteResourceResponse response = client.deleteResource(deleteResourceRequest);
            System.out.println("Delete resource response received successfully:");
            System.out.println(response.toString());
        } catch (StatusRuntimeException statusException) {
            System.out.println("gRPC error occurred during Delete resource:");
            statusException.printStackTrace();
        } finally {
            clientAndChannel.getRight().shutdown();
        }
    }
}
