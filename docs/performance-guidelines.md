# Performance Guidelines

## Scope

This document covers concurrency patterns, resource lifecycle, and performance rules specific to `kessel-sdk-java`. It is not a general concurrency tutorial.

## 1. Token Management (`OAuth2ClientCredentials`)

### Architecture

`OAuth2ClientCredentials` implements a double-checked locking pattern for thread-safe token caching:

- `tokenCache` is declared `volatile` to guarantee visibility across threads.
- A `ReentrantLock` (`refreshLock`) serializes actual token refresh calls.
- `isCacheValid()` reads the volatile field without locking (fast path).
- `getToken()` only acquires `refreshLock` when the cache is invalid, then re-checks validity inside the lock (double-check) to avoid redundant refreshes.

The 5-minute `EXPIRATION_WINDOW` causes proactive refresh before actual expiry, preventing auth failures under load.

### Rules

- **R1**: Create one `OAuth2ClientCredentials` instance per logical service identity. Share it across all stubs and threads. The class is designed for concurrent access.
- **R2**: Never call `getToken(true)` (force-refresh) in a hot path. Force-refresh bypasses the volatile fast path and always acquires the lock, serializing all callers.
- **R3**: Do not wrap `getToken()` in external synchronization. The internal `ReentrantLock` already prevents stampede; external locks cause unnecessary contention.
- **R4**: If token refresh fails, `OAuth2Exception` (a `RuntimeException`) propagates to the gRPC `MetadataApplier` as `UNAUTHENTICATED`. Callers should handle `StatusRuntimeException` with `Status.UNAUTHENTICATED` and implement retry logic at the application level.
- **R5**: `RefreshTokenResponse` and `ClientConfigAuth` are Java records (immutable). They are inherently thread-safe and safe to cache or pass between threads.

### Token flow through gRPC

`OAuth2CallCredentials.applyRequestMetadata()` calls `auth.getToken()` on the gRPC-provided `Executor`. This means:
- Token reads happen off the calling thread.
- The volatile fast path means most calls return immediately without blocking the executor thread.
- Only expired-token scenarios cause a blocking HTTP call to the OAuth server.

## 2. Channel Lifecycle (`AbstractClientBuilder`)

### Architecture

`AbstractClientBuilder.build()` and `buildAsync()` each create a new `ManagedChannel` via `Grpc.newChannelBuilder()`. The builder returns a `Pair<Stub, ManagedChannel>`, giving the caller ownership of both the stub and the channel.

Key design decisions:
- Every `build()`/`buildAsync()` call creates a **new** channel. There is no internal channel pool or cache.
- `TlsChannelCredentials` is the default when no `ChannelCredentials` are provided.
- `CompositeChannelCredentials` is used to combine channel-level TLS with call-level OAuth2 credentials.
- The builder validates that `CallCredentials` (authentication) cannot be used with `InsecureChannelCredentials`.

### Rules

- **R6**: Reuse channels. Do not call `build()` per-request. `ManagedChannel` maintains an HTTP/2 connection pool internally. Creating one per request wastes connections, causes TCP handshake overhead, and leaks resources if not shut down.
- **R7**: The caller owns the `ManagedChannel` returned in the `Pair`. You must call `channel.shutdown()` when done (see Section 4).
- **R8**: A single `ManagedChannel` supports multiplexed requests. One channel per target endpoint is sufficient for most applications. gRPC handles concurrent RPCs over the same HTTP/2 connection.
- **R9**: Both blocking stubs (`build()`) and async stubs (`buildAsync()`) share the same channel construction. Choose blocking for simple request-response; choose async for StreamObserver-based streaming or when you need non-blocking behavior.
- **R10**: The `ClientBuilder` is not thread-safe during construction (mutable builder pattern). Build it on a single thread, then share the resulting stub and channel freely.

## 3. Blocking vs. Async Stubs

### Blocking stubs (`build()`)

Used in `CheckExample`, `CheckForUpdateExample`, `StreamedListObjectsExample`, `ReportResourceExample`, `DeleteResourceExample`, `CheckBulkExample`, and `AuthExample`. Blocking stubs:
- Block the calling thread until the response arrives (unary RPCs) or until the stream ends (server-streaming via `Iterator`).
- Are simpler but tie up a thread per outstanding RPC.

### Async stubs (`buildAsync()`)

Used in `AsyncExample`. Async stubs:
- Use `StreamObserver` callbacks for responses.
- Do not block the calling thread.
- Require careful lifecycle management: shut down the channel in `onCompleted()` and `onError()`.

### Rules

- **R11**: For server-streaming RPCs (e.g., `streamedListObjects`), prefer blocking `Iterator`-based consumption when throughput requirements are modest. The `StreamedListObjectsExample` pattern (iterate with `hasNext()`/`next()` in try/finally) is correct and handles backpressure automatically.
- **R12**: For high-throughput or fan-out patterns, use async stubs. But always shut down the channel in both `onCompleted()` and `onError()` callbacks, as shown in `AsyncExample`.
- **R13**: Never ignore `onError()` in `StreamObserver` implementations. An unhandled error leaves the channel in an undefined state and may leak resources.
- **R14**: For batch operations, prefer `checkBulk()` (single RPC with multiple items) over issuing parallel individual `check()` calls. This reduces round-trips and connection overhead.

## 4. Resource Cleanup

### Rules

- **R15**: Always shut down `ManagedChannel` in a `finally` block (blocking) or in both `onCompleted()`/`onError()` (async). Every example in this repo follows this pattern. Failing to shut down leaks threads and file descriptors.
- **R16**: Call `channel.shutdown()`, not `channel.shutdownNow()`, unless you need to cancel in-flight RPCs. `shutdown()` allows pending RPCs to complete gracefully.
- **R17**: For long-lived services, shut down channels during application shutdown (e.g., via a shutdown hook or container lifecycle callback). Do not rely on JVM finalization.
- **R18**: `OAuth2ClientCredentials` holds no closeable resources itself (the HTTP calls for token refresh are synchronous and self-contained). No explicit cleanup is needed for the auth layer.

## 5. Nimbus OAuth2 as Optional Dependency

The `oauth2-oidc-sdk` dependency is declared `<optional>true</optional>` in `pom.xml`. This means:

- **R19**: If your application does not use OAuth2 authentication (e.g., `insecure()` or custom `CallCredentials`), you do not need `oauth2-oidc-sdk` on the classpath.
- **R20**: `OAuth2ClientCredentials` constructor performs a runtime `Class.forName()` check for Nimbus availability and throws `OAuth2Exception` with a clear message if missing. This fail-fast behavior prevents cryptic `NoClassDefFoundError` at token refresh time.

## 6. OIDC Discovery

`OIDCDiscovery.fetchOIDCDiscovery()` is a static, stateless method that performs an HTTP call to resolve the token endpoint from an issuer URL.

- **R21**: Cache the returned `OIDCDiscoveryMetadata` (it is an immutable record). Do not call `fetchOIDCDiscovery()` repeatedly; the token endpoint does not change at runtime.
- **R22**: Call discovery once at startup, extract the `tokenEndpoint`, and pass it to `ClientConfigAuth`. See `AuthExample` for the canonical pattern.

## 7. Credential Security Validation

`AbstractClientBuilder.validateCredentials()` enforces that `CallCredentials` (bearer tokens) cannot be combined with `InsecureChannelCredentials`. This prevents accidentally sending tokens over plaintext connections.

- **R23**: If you need authentication, you must use TLS (the default) or provide custom secure `ChannelCredentials`. The builder will throw `IllegalStateException` at build time if this invariant is violated.

## 8. Summary of Thread-Safety Guarantees

| Component | Thread-safe? | Notes |
|---|---|---|
| `OAuth2ClientCredentials` | Yes | volatile + ReentrantLock double-check |
| `OAuth2CallCredentials` | Yes | Stateless; delegates to thread-safe `OAuth2ClientCredentials` |
| `ClientConfigAuth` | Yes | Immutable record |
| `RefreshTokenResponse` | Yes | Immutable record |
| `OIDCDiscoveryMetadata` | Yes | Immutable record |
| `AbstractClientBuilder` | No | Mutable builder; use on one thread, then share the built artifacts |
| `ManagedChannel` | Yes | gRPC channels are thread-safe |
| Blocking stubs | Yes | gRPC stubs are thread-safe and reusable |
| Async stubs | Yes | gRPC stubs are thread-safe and reusable |
