package org.project_kessel.api.grpc;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import org.project_kessel.api.auth.OAuth2Exception;

import java.util.Objects;
import java.util.concurrent.Executor;

public class OAuth2CallCredentials {

    private static final Metadata.Key<String> AUTHORIZATION_KEY = Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER);

    public static CallCredentials oauth2CallCredentials(org.project_kessel.api.auth.OAuth2ClientCredentials auth) {
        Objects.requireNonNull(auth, "auth must not be null");
        return new CallCredentials() {
            @Override
            public void applyRequestMetadata(RequestInfo requestInfo, Executor executor, MetadataApplier metadataApplier) {
                executor.execute(() -> {
                    try {
                        String token = auth.getToken().accessToken();
                        Metadata metadata = new Metadata();
                        metadata.put(AUTHORIZATION_KEY, String.format("Bearer %s", token));
                        metadataApplier.apply(metadata);
                    } catch (OAuth2Exception ex) {
                        metadataApplier.fail(Status.UNAUTHENTICATED.withDescription(ex.getMessage()));
                    }
                });
            }
        };
    }
}
