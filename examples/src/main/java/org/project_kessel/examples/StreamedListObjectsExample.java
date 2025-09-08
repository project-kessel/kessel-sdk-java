package org.project_kessel.examples;

import com.nimbusds.jose.util.Pair;
import io.grpc.ManagedChannel;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.BlockingClientCall;
import org.project_kessel.api.inventory.v1beta2.*;
import org.project_kessel.examples.util.EnvConfig;

import java.util.Iterator;

import static org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;

public class StreamedListObjectsExample {

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
            StreamedListObjectsRequest streamedListObjectsRequest = StreamedListObjectsRequest
                    .newBuilder()
                    .setObjectType(
                            RepresentationType
                                    .newBuilder()
                                    .setResourceType("workspace")
                                    .setReporterType("rbac")
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


            Iterator<StreamedListObjectsResponse> response = client.streamedListObjects(streamedListObjectsRequest);
            System.out.println("Response from Streamed list objects");
            while (response.hasNext()) {
                System.out.println("Stream list objects response received successfully:" + response.next());
            }
        } catch (StatusRuntimeException statusException) {
            System.out.println("gRPC error occurred during Stream list objects:");
            statusException.printStackTrace();
        } finally {
            clientAndChannel.getRight().shutdown();
        }
    }
}
