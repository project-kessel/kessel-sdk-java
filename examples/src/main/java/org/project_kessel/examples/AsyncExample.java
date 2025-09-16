package org.project_kessel.examples;

import com.nimbusds.jose.util.Pair;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.project_kessel.api.inventory.v1beta2.*;
import org.project_kessel.examples.util.EnvConfig;

import static org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceStub;

public class AsyncExample {

    public static void main(String[] args) {
        // Validate required environment variables
        EnvConfig.validateRequired(
            "KESSEL_ENDPOINT"
        );

        // Load configuration from environment/.env file
        String kesselEndpoint = EnvConfig.get("KESSEL_ENDPOINT");

        Pair<KesselInventoryServiceStub, ManagedChannel> clientAndChannel = new ClientBuilder(kesselEndpoint)
                .insecure()
                .buildAsync();
        KesselInventoryServiceStub client = clientAndChannel.getLeft();

        CheckRequest checkRequest = CheckRequest
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

        client.check(checkRequest, new StreamObserver<CheckResponse>() {
            @Override
            public void onNext(CheckResponse checkResponse) {
                System.out.println("Check response received successfully:");
                System.out.println(checkResponse.toString());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("gRPC error occurred during Check:");
                throwable.printStackTrace();
                clientAndChannel.getRight().shutdown();
            }

            @Override
            public void onCompleted() {
                clientAndChannel.getRight().shutdown();
            }
        });
    }
}
