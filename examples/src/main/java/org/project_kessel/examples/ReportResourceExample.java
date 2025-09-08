package org.project_kessel.examples;

import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.nimbusds.jose.util.Pair;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.project_kessel.api.inventory.v1beta2.*;
import org.project_kessel.examples.util.EnvConfig;

import java.util.Map;

import static org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;

public class ReportResourceExample {

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
            ReportResourceRequest reportResourceRequest = ReportResourceRequest
                    .newBuilder()
                    .setType("host")
                    .setReporterType("hbi")
                    .setReporterInstanceId("0a2a430e-1ad9-4304-8e75-cc6fd3b5441a")
                    .setRepresentations(
                            ResourceRepresentations
                                    .newBuilder()
                                    .setMetadata(
                                            RepresentationMetadata
                                                    .newBuilder()
                                                    .setLocalResourceId("854589f0-3be7-4cad-8bcd-45e18f33cb81")
                                                    .setApiHref("https://apiHref.com/")
                                                    .setConsoleHref("https://www.consoleHref.com/")
                                                    .setReporterVersion("0.2.11")
                                                    .build()
                                    )
                                    .setCommon(
                                            Struct
                                                    .newBuilder()
                                                    .putAllFields(
                                                            Map.of("workspace_id", Value.newBuilder().setStringValue("6eb10953-4ec9-4feb-838f-ba43a60880bf").build())
                                                    )
                                                    .build()
                                    )
                                    .setReporter(Struct
                                            .newBuilder()
                                            .putAllFields(
                                                    Map.of(
                                                            "satellite_id", Value.newBuilder().setStringValue("ca234d8f-9861-4659-a033-e80460b2801c").build(),
                                                            "sub_manager_id", Value.newBuilder().setStringValue("e9b7d65f-3f81-4c26-b86c-2db663376eed").build(),
                                                            "insights_inventory_id", Value.newBuilder().setStringValue("c4b9b5e7-a82a-467a-b382-024a2f18c129").build(),
                                                            "ansible_host", Value.newBuilder().setStringValue("host-1").build()
                                                    )
                                            )
                                            .buildPartial()
                                    )
                                    .build()
                    )
                    .build();

            ReportResourceResponse response = client.reportResource(reportResourceRequest);
            System.out.println("Report resource response received successfully:");
            System.out.println(response.toString());
        } catch (StatusRuntimeException statusException) {
            System.out.println("gRPC error occurred during Report resource:");
            statusException.printStackTrace();
        } finally {
            clientAndChannel.getRight().shutdown();
        }
    }
}
