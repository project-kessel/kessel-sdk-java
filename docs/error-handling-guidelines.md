# Error Handling Guidelines

## Exception Hierarchy

This SDK uses three categories of exceptions. Follow these rules strictly.

### 1. `OAuth2Exception` (unchecked -- extends `RuntimeException`)

- **Location**: `org.project_kessel.api.auth.OAuth2Exception`
- **Purpose**: All OAuth2/OIDC authentication failures.
- **Constructors**: `(String message)` and `(String message, Throwable cause)`.
- **Rule**: Always wrap lower-level auth errors (Nimbus `ParseException`, `IOException`, `GeneralException`, `ClassNotFoundException`) into `OAuth2Exception` with a descriptive message. Never let Nimbus exceptions leak to callers.
- **Rule**: When a library dependency is missing at runtime, throw `OAuth2Exception` with an actionable message naming the missing Maven artifact (e.g., `"Add dependency: com.nimbusds:oauth2-oidc-sdk"`).

### 2. `StatusRuntimeException` (gRPC -- unchecked)

- **Source**: `io.grpc.StatusRuntimeException`, thrown by gRPC blocking stubs.
- **Rule**: This is the primary exception type for all gRPC call failures. Catch it explicitly by type in application code and examples.
- **Rule**: In the async path (`StreamObserver`), gRPC errors arrive via `onError(Throwable)`. The `Throwable` is typically a `StatusRuntimeException`. Handle it there and clean up the channel.

### 3. Standard Java exceptions for configuration and HTTP errors

- `NullPointerException` via `Objects.requireNonNull` -- used for all constructor/parameter null validation in records (`ClientConfigAuth`, `RefreshTokenResponse`, `OIDCDiscoveryMetadata`) and builders (`AbstractClientBuilder`).
- `IllegalStateException` -- thrown by `AbstractClientBuilder.validateCredentials()` when call credentials are combined with insecure channel credentials. Also thrown by `EnvConfig.validateRequired()` for missing environment variables.
- `IOException` -- thrown by `FetchWorkspace` for HTTP-level failures (non-200 status codes, unexpected response shapes).

## OAuth2 Error Flow

The OAuth2 token lifecycle has three layers, each with specific wrapping rules.

### OIDC Discovery (`OIDCDiscovery.fetchOIDCDiscovery`)

Wraps four distinct failure modes into `OAuth2Exception`:
- `ClassNotFoundException` -- Nimbus library not on classpath.
- `IOException` -- Network failure reaching the issuer.
- `ParseException` -- Malformed OIDC discovery JSON.
- `GeneralException` -- Token endpoint not found in metadata.

**Rule**: Always call `fetchOIDCDiscovery` before constructing `OAuth2ClientCredentials`. Let the `OAuth2Exception` propagate -- do not catch and ignore it.

### Token Refresh (`OAuth2ClientCredentials.getToken` / `.refresh`)

- Uses double-checked locking with `ReentrantLock` for thread safety.
- The `refresh()` method throws `OAuth2Exception` for: token request failure (error response from server), null access token, or `ParseException`/`IOException` from the HTTP call.
- **Rule**: The `getToken()` method signature declares `throws OAuth2Exception`. Always propagate it -- never silently return null or a stale token.

### gRPC Credential Injection (`OAuth2CallCredentials`)

- Catches `OAuth2Exception` inside the `CallCredentials.applyRequestMetadata` executor callback.
- Converts it to `Status.UNAUTHENTICATED` via `metadataApplier.fail(Status.UNAUTHENTICATED.withDescription(ex.getMessage()))`.
- **Rule**: This is the only place where `OAuth2Exception` is translated to a gRPC `Status`. The resulting `StatusRuntimeException` will surface to callers of blocking stubs. Do not add additional auth-to-gRPC translation layers.

## gRPC Error Handling Patterns

### Blocking Stub Pattern

```java
try {
    Response response = client.someRpc(request);
    // process response
} catch (StatusRuntimeException statusException) {
    // log/handle gRPC error
} finally {
    channel.shutdown();
}
```

**Rules**:
- Always catch `StatusRuntimeException` specifically in application code and examples.
- Always shut down the `ManagedChannel` in a `finally` block.
- The `build()` method returns `Pair<Stub, ManagedChannel>`. The caller owns the channel lifecycle.

### Async Stub Pattern (StreamObserver)

```java
client.check(request, new StreamObserver<CheckResponse>() {
    public void onNext(CheckResponse response) { /* process */ }
    public void onError(Throwable throwable) {
        // handle error + shutdown channel
    }
    public void onCompleted() {
        channel.shutdown();
    }
});
```

**Rules**:
- Shut down the channel in both `onError` and `onCompleted`.
- The `throwable` in `onError` is typically `StatusRuntimeException`. Check via `instanceof` if you need to extract the gRPC `Status`.

## Bulk Response Error Handling

Bulk operations (`checkBulk`, `checkSelfBulk`, `checkForUpdateBulk`) return per-item errors rather than throwing. All three follow the identical protobuf pattern.

### Response Structure

Each bulk response contains a list of `ResponsePair` objects. Each pair uses a protobuf `oneof` with two cases:
- `ITEM` -- successful result (`pair.hasItem()` returns `true`).
- `ERROR` -- per-item failure (`pair.hasError()` returns `true`), containing a `com.google.rpc.Status` with `code` and `message`.

### Required Handling Pattern

```java
for (int i = 0; i < response.getPairsCount(); i++) {
    CheckBulkResponsePair pair = response.getPairs(i);
    if (pair.hasItem()) {
        // process pair.getItem()
    } else if (pair.hasError()) {
        com.google.rpc.Status error = pair.getError();
        // handle error.getCode() and error.getMessage()
    }
}
```

**Rules**:
- Always check both `hasItem()` and `hasError()` for every pair. Never assume all items succeed.
- A `StatusRuntimeException` can still be thrown for request-level failures (e.g., network error, auth failure). The per-item errors only cover server-side evaluation failures for individual items.
- The `com.google.rpc.Status` here is distinct from `io.grpc.Status`. It is a protobuf message, not a gRPC status object.

## Iterator-Based Error Handling (ListWorkspaces)

`ListWorkspaces` returns `Iterable<StreamedListObjectsResponse>` backed by `WorkspaceListIterator`, which handles paginated gRPC streaming internally.

### Error Wrapping

The iterator's `tryToFetchNext()` catches any `Exception` from the gRPC call and wraps it in `RuntimeException("Error fetching workspaces: " + e.getMessage(), e)`.

**Rules**:
- Errors are deferred -- they surface when iterating (calling `hasNext()`/`next()`), not when calling `listWorkspaces()`.
- Catch `RuntimeException` around iteration, then inspect the cause for `StatusRuntimeException` if you need gRPC status details.
- The iterator throws `NoSuchElementException` if `next()` is called when no more elements exist. Always use `hasNext()` or for-each loops.
- The `Iterable` is reusable -- each call to `iterator()` creates a new `WorkspaceListIterator`, allowing multiple independent iterations.

## FetchWorkspace HTTP Error Handling

`FetchWorkspace` uses Java's `HttpClient` (not gRPC) and declares `throws IOException, InterruptedException, OAuth2Exception`.

**Rules**:
- Non-200 HTTP status codes throw `IOException` with the status code in the message.
- Unexpected response shapes (zero or multiple workspaces when exactly one is expected) throw `IOException`.
- `OAuth2Exception` can be thrown during `auth.configureRequest()` if token acquisition fails.
- All three checked exceptions must be handled by the caller. This is the only API surface in the SDK that uses checked exceptions for error propagation.

## ClientBuilder Credential Validation

`AbstractClientBuilder.validateCredentials()` throws `IllegalStateException` when call credentials (authentication tokens) are combined with insecure (plaintext) channel credentials. This is a security guardrail.

**Rule**: Never bypass this validation. If you need insecure mode for local development, use `.insecure()` without authentication. If you need authentication, use TLS (the default) or provide explicit `TlsChannelCredentials`.

## Null Validation Conventions

All records and key constructors use `Objects.requireNonNull` with descriptive messages:
- `ClientConfigAuth`: validates `clientId`, `clientSecret`, `tokenEndpoint`.
- `RefreshTokenResponse`: validates `accessToken`, `expiresAt`.
- `OIDCDiscoveryMetadata`: validates `tokenEndpoint`.
- `AbstractClientBuilder`: validates `target`.
- `OAuth2CallCredentials`: validates `auth`.

**Rule**: Never pass null to these constructors. The `NullPointerException` thrown is intentional and immediate -- do not catch and convert it.

## Test Coverage Rules

- Test OAuth2 error paths by asserting `OAuth2Exception` for invalid/unreachable URLs (`OIDCDiscoveryTest`).
- Test null parameter rejection by asserting `NullPointerException` on records and builders (`OAuth2ClientCredentialsTest`, `AbstractClientBuilderTest`, `FetchWorkspaceTest`).
- Test iterator error propagation by mocking the gRPC stub to throw, then asserting the wrapped `RuntimeException` contains `"Error fetching workspaces"` (`ListWorkspacesTest.testHandlesGrpcError`).
- Test HTTP error responses by mocking `HttpClient` to return non-200 status codes or unexpected JSON shapes (`FetchWorkspaceTest`).
- When adding new error-handling tests, follow the existing pattern of asserting the specific exception type and verifying the error message content with `assertTrue(e.getMessage().contains(...))`.
