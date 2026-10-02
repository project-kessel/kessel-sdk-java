package org.project_kessel.examples;

import com.nimbusds.jose.util.Pair;
import io.grpc.ManagedChannel;
import org.project_kessel.api.inventory.v1beta2.ClientBuilder;
import org.project_kessel.api.inventory.v1beta2.KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;
import org.project_kessel.examples.util.EnvConfig;

import java.time.Duration;

public class KeepaliveExample {

    public static void main(String[] args) {
        EnvConfig.validateRequired("KESSEL_ENDPOINT");
        String kesselEndpoint = EnvConfig.get("KESSEL_ENDPOINT");

        Pair<KesselInventoryServiceBlockingStub, ManagedChannel> clientAndChannel = new ClientBuilder(kesselEndpoint)
                .keepaliveInterval(Duration.ofSeconds(60))
                .keepaliveTimeout(Duration.ofSeconds(15))
                .keepalivePermitWithoutCalls(false)
                .build();

        try {
            System.out.println("Keepalive overrides: interval=60s, timeout=15s, permitWithoutCalls=false.");
            System.out.println("Without these setters, build() and buildAsync() default to 45s, 10s, and true.");
            System.out.println("This example configures the channel but does not issue an RPC.");
        } finally {
            clientAndChannel.getRight().shutdown();
        }
    }
}
