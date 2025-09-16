package org.project_kessel.examples;

import com.nimbusds.jose.util.Pair;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.project_kessel.api.auth.ClientConfigAuth;
import org.project_kessel.api.auth.OAuth2ClientCredentials;
import org.project_kessel.api.auth.OIDCDiscovery;
import org.project_kessel.api.auth.OIDCDiscoveryMetadata;
import org.project_kessel.api.inventory.v1beta2.ResourceReference;
import org.project_kessel.api.inventory.v1beta2.ClientBuilder;
import org.project_kessel.api.inventory.v1beta2.CheckRequest;
import org.project_kessel.api.inventory.v1beta2.CheckResponse;
import org.project_kessel.api.inventory.v1beta2.ReporterReference;
import org.project_kessel.api.inventory.v1beta2.SubjectReference;
import org.project_kessel.examples.util.EnvConfig;

import static org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;

public class AuthExample {

    public static void main(String[] args) {
        // Validate required environment variables
        EnvConfig.validateRequired(
            "AUTH_DISCOVERY_ISSUER_URL",
            "AUTH_CLIENT_ID",
            "AUTH_CLIENT_SECRET",
            "KESSEL_ENDPOINT"
        );

        // Load configuration from environment/.env file
        String issuerUrl = EnvConfig.get("AUTH_DISCOVERY_ISSUER_URL");
        String clientId = EnvConfig.get("AUTH_CLIENT_ID");
        String clientSecret = EnvConfig.get("AUTH_CLIENT_SECRET");
        String kesselEndpoint = EnvConfig.get("KESSEL_ENDPOINT");

        OIDCDiscoveryMetadata discovery = OIDCDiscovery.fetchOIDCDiscovery(issuerUrl);
        ClientConfigAuth authConfig = new ClientConfigAuth(
                clientId,
                clientSecret,
                discovery.tokenEndpoint()
        );
        OAuth2ClientCredentials oauthClient = new OAuth2ClientCredentials(authConfig);

        Pair<KesselInventoryServiceBlockingStub, ManagedChannel> clientAndChannel = new ClientBuilder(kesselEndpoint)
                .oauth2ClientAuthenticated(oauthClient)
                .build();
        KesselInventoryServiceBlockingStub client = clientAndChannel.getLeft();

        try {
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

            CheckResponse response = client.check(checkRequest);
            System.out.println("Check response received successfully:");
            System.out.println(response.toString());
        } catch (StatusRuntimeException statusException) {
            System.out.println("gRPC error occurred during Check:");
            statusException.printStackTrace();
        } finally {
            clientAndChannel.getRight().shutdown();
        }
    }
}
