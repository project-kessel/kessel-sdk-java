# Integration Guidelines

## Architecture Overview

The SDK is a multi-module Maven project (`kessel-sdk-parent`) containing:
- `kessel-sdk` -- the published library (artifact `org.project-kessel:kessel-sdk`)
- `examples` -- runnable consumer examples (not published)

Two API versions are active:
- **`v1`** -- Health service only (`KesselInventoryHealthServiceGrpc`)
- **`v1beta2`** -- Primary inventory service (`KesselInventoryServiceGrpc`): Check, CheckSelf, CheckForUpdate, CheckBulk, CheckSelfBulk, CheckForUpdateBulk, ReportResource, DeleteResource, StreamedListObjects, StreamedListSubjects

Legacy `v1beta1` types exist (authz, resources, relationships) but have no `ClientBuilder` and should not be used for new integrations.

## Client Builder Pattern

### Core Hierarchy

`AbstractClientBuilder<BT, AT>` (`org.project_kessel.api.inventory.AbstractClientBuilder`) is the single base class. It is parameterized on the blocking stub type (`BT`) and async stub type (`AT`).

Each API version provides a concrete `ClientBuilder` that extends it:
- `org.project_kessel.api.inventory.v1.ClientBuilder` -- builds `KesselInventoryHealthServiceBlockingStub` / `KesselInventoryHealthServiceStub`
- `org.project_kessel.api.inventory.v1beta2.ClientBuilder` -- builds `KesselInventoryServiceBlockingStub` / `KesselInventoryServiceStub`

### Rules for Using ClientBuilder

1. **Always provide the target as a constructor argument.** It is validated non-null at construction time.
   ```java
   new ClientBuilder("localhost:9000")
   ```

2. **Call exactly one credential method before building.** Choose one of:
   - `.insecure()` -- `InsecureChannelCredentials`, no auth. For local dev only.
   - `.unauthenticated()` / `.unauthenticated(channelCredentials)` -- TLS (default) or custom channel creds, no call credentials.
   - `.authenticated(callCredentials)` / `.authenticated(callCredentials, channelCredentials)` -- custom `CallCredentials` with TLS or custom channel.
   - `.oauth2ClientAuthenticated(oAuth2ClientCredentials)` / `.oauth2ClientAuthenticated(oAuth2ClientCredentials, channelCredentials)` -- SDK-managed OAuth2 token injection.

3. **The builder rejects insecure channels combined with call credentials.** `validateCredentials()` throws `IllegalStateException` if you set `CallCredentials` on a non-TLS channel.

4. **When no `ChannelCredentials` are supplied, `TlsChannelCredentials.create()` is the default.** You never get a plaintext channel unless you explicitly call `.insecure()`.

5. **`build()` returns `Pair<BlockingStub, ManagedChannel>`.** `buildAsync()` returns `Pair<AsyncStub, ManagedChannel>`. Always destructure both: the stub for RPC calls, the channel for lifecycle management.

### Adding a New Service ClientBuilder

Extend `AbstractClientBuilder` with the two concrete stub types and implement `newStub(Channel)` and `newAsyncStub(Channel)`. Follow the exact pattern in `v1beta2/ClientBuilder.java` (9 lines total).

## Channel Lifecycle and Cleanup

### Rules

1. **The caller owns the `ManagedChannel`.** The builder creates it; the caller must shut it down.
2. **Always call `channel.shutdown()` in a `finally` block** (blocking examples) or in `onCompleted()`/`onError()` (async examples).
3. **One channel per builder invocation.** Do not reuse channels across multiple `build()` calls; each call creates a fresh `ManagedChannel`.

## Authentication

### OAuth2 Client Credentials Flow

The full chain is: `OIDCDiscovery` -> `ClientConfigAuth` -> `OAuth2ClientCredentials` -> `ClientBuilder.oauth2ClientAuthenticated()`.

```java
OIDCDiscoveryMetadata discovery = OIDCDiscovery.fetchOIDCDiscovery(issuerUrl);
ClientConfigAuth authConfig = new ClientConfigAuth(clientId, clientSecret, discovery.tokenEndpoint());
OAuth2ClientCredentials oauthClient = new OAuth2ClientCredentials(authConfig);
// Then pass to builder:
new ClientBuilder(endpoint).oauth2ClientAuthenticated(oauthClient).build();
```

### Rules

1. **`ClientConfigAuth` is a record requiring all three fields non-null:** `clientId`, `clientSecret`, `tokenEndpoint`.
2. **`OAuth2ClientCredentials` handles token caching internally** with a 5-minute expiration window and `ReentrantLock`-based thread-safe refresh. Do not wrap it in your own caching layer.
3. **`OIDCDiscovery.fetchOIDCDiscovery()` is a static helper** that resolves the token endpoint from an issuer URL. It throws `OAuth2Exception` on network/parse failure.
4. **Nimbus OAuth SDK (`com.nimbusds:oauth2-oidc-sdk`) is an optional dependency.** If absent at runtime, `OAuth2ClientCredentials` and `OIDCDiscovery` throw `OAuth2Exception` with a clear message. Consumers using OAuth must add this dependency explicitly.
5. **`OAuth2CallCredentials.oauth2CallCredentials()`** wraps `OAuth2ClientCredentials` into a gRPC `CallCredentials` that injects `Bearer <token>` into the `authorization` metadata key per-call.

### REST API Authentication

For non-gRPC calls (e.g., `FetchWorkspace`), use `OAuth2AuthRequest` which implements the `AuthRequest` interface and injects the Bearer token into `HttpRequest.Builder` headers.

## Streaming Integration

### Server-Side Streaming Methods

`StreamedListObjects` and `StreamedListSubjects` are the only server-streaming RPCs. They are `SERVER_STREAMING` method type.

### Blocking Consumption Pattern

Use the `BlockingStub` -- it returns `Iterator<StreamedListObjectsResponse>`:

```java
Iterator<StreamedListObjectsResponse> response = client.streamedListObjects(request);
while (response.hasNext()) {
    StreamedListObjectsResponse item = response.next();
}
```

### Async Consumption Pattern

Use the `AsyncStub` with a `StreamObserver`:

```java
client.streamedListObjects(request, new StreamObserver<>() {
    public void onNext(StreamedListObjectsResponse value) { /* process each */ }
    public void onError(Throwable t) { channel.shutdown(); }
    public void onCompleted() { channel.shutdown(); }
});
```

### Paginated Streaming (ListWorkspaces Pattern)

`ListWorkspaces` in `org.project_kessel.api.rbac.v2` wraps `streamedListObjects` with automatic continuation-token pagination:
- Uses `RequestPagination` with `limit=1000` and `continuationToken`
- Returns `Iterable<StreamedListObjectsResponse>` for enhanced for-loop consumption
- Stops when `continuationToken` is empty or no more results

Follow this pattern for any paginated streaming consumer.

## RBAC Utility Helpers

`org.project_kessel.api.rbac.v2.Utils` provides factory methods for common protobuf objects:
- `Utils.workspaceType()` -- `RepresentationType` for workspaces
- `Utils.roleType()` -- `RepresentationType` for roles
- `Utils.workspaceResource(resourceId)` -- `ResourceReference` for a workspace
- `Utils.roleResource(resourceId)` -- `ResourceReference` for a role
- `Utils.principalSubject(id, domain)` -- `SubjectReference` with format `domain/id`
- `Utils.principalResource(id, domain)` -- `ResourceReference` for a principal
- `Utils.subject(resourceRef, relation)` -- `SubjectReference` with optional relation

**Rule:** Use these helpers instead of manually constructing `ResourceReference`/`SubjectReference` for RBAC resource types. The reporter type is always `"rbac"`.

## Health Check Integration

Use `v1.ClientBuilder` to build a health client separately from the inventory client:

```java
Pair<KesselInventoryHealthServiceBlockingStub, ManagedChannel> health =
    new org.project_kessel.api.inventory.v1.ClientBuilder(endpoint).insecure().build();
health.getLeft().getLivez(GetLivezRequest.getDefaultInstance());
health.getLeft().getReadyz(GetReadyzRequest.getDefaultInstance());
```

The health service has two endpoints: `GetLivez` (liveness) and `GetReadyz` (readiness). Both return `status` (string) and `code` (uint32).

## Error Handling Conventions

1. **Blocking stubs throw `StatusRuntimeException`.** Always catch this in try blocks around RPC calls.
2. **Async stubs deliver errors via `StreamObserver.onError(Throwable)`.** The throwable is typically `StatusRuntimeException`.
3. **OAuth errors throw `OAuth2Exception`** (extends `RuntimeException`) during token acquisition. This surfaces through `CallCredentials` as `Status.UNAUTHENTICATED`.
4. **Bulk operations** (CheckBulk, CheckSelfBulk) return per-item errors in the response (`google.rpc.Status`) rather than failing the entire RPC. Check `pair.hasError()` on each response pair.

## Configuration via Environment

Examples use `EnvConfig` (dotenv-based) with these variables:
- `KESSEL_ENDPOINT` -- gRPC target (e.g., `localhost:9000`)
- `AUTH_CLIENT_ID`, `AUTH_CLIENT_SECRET` -- OAuth2 credentials
- `AUTH_DISCOVERY_ISSUER_URL` -- OIDC issuer for token endpoint discovery
- `RBAC_BASE_ENDPOINT` -- HTTP base URL for REST RBAC calls (e.g., `FetchWorkspace`)

## Consumer Dependency

```xml
<dependency>
    <groupId>org.project-kessel</groupId>
    <artifactId>kessel-sdk</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

Consumers must also add a gRPC transport runtime (e.g., `grpc-netty-shaded`) and, if using OAuth, `com.nimbusds:oauth2-oidc-sdk`. The SDK declares Nimbus as `<optional>true</optional>`.
