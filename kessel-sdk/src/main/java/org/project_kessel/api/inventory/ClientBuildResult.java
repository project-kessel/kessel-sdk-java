package org.project_kessel.api.inventory;

import io.grpc.ManagedChannel;

import java.util.Objects;

/**
 * Result of building a gRPC client via {@link AbstractClientBuilder}.
 * Holds the stub and the channel that must be shut down by the caller.
 *
 * @param stub    the gRPC stub (blocking or async)
 * @param channel the managed channel — caller must call {@code channel.shutdown()} when done
 * @param <T>     the stub type
 */
public record ClientBuildResult<T>(T stub, ManagedChannel channel) {

    public ClientBuildResult {
        Objects.requireNonNull(stub, "stub must not be null");
        Objects.requireNonNull(channel, "channel must not be null");
    }
}
