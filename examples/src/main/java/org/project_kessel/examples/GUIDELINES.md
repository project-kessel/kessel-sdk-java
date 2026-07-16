# Examples Package Guidelines

Rules for the `examples` module -- runnable code samples demonstrating SDK usage. This module is compiled but never published.

## Module Configuration

- Artifact: `kessel-sdk-examples`. Not published: `maven.deploy.skip=true`, `central.skip=true`.
- Dependencies: `kessel-sdk` (sibling module), `grpc-okhttp` (transport), `oauth2-oidc-sdk` (non-optional here), `dotenv-java` (env config).
- Dependency versions come from the parent POM properties. Exception: `dotenv-java` version is declared directly in this POM (examples-only dependency, no parent property).
- Examples are compiled as part of `./mvnw clean verify` but require a live Kessel server to run. They are not automated tests.

## File Naming and Structure

- Each example is a standalone class: `<Feature>Example.java` (e.g., `CheckExample.java`, `AsyncExample.java`).
- Package: `org.project_kessel.examples`. Utilities go in `org.project_kessel.examples.util`.
- Every example must have a `public static void main(String[] args)` entry point.

## Maven Profiles for Running

- Each example needs a corresponding Maven profile in `examples/pom.xml` using `exec-maven-plugin`.
- Profile ID pattern: `run-<feature-name>` (e.g., `run-auth`, `run-check`, `run-async`, `run-fetch-workspace`).
- Run with: `./mvnw -pl examples exec:java -P run-<profile-name>`.
- Use `exec-maven-plugin` version `3.6.3`. Note: `run-console-principal` currently uses `3.6.2` -- this should be standardized to `3.6.3`.
- The `mainClass` configuration points to the fully qualified class name.

## Environment Configuration

- Use `EnvConfig` utility (`org.project_kessel.examples.util.EnvConfig`) for all configuration.
- Call `EnvConfig.validateRequired(...)` at the top of `main()` for required variables before any other work.
- Never hardcode endpoints, secrets, or credentials. Always load from environment or `.env` file.
- Standard variables: `KESSEL_ENDPOINT`, `AUTH_CLIENT_ID`, `AUTH_CLIENT_SECRET`, `AUTH_DISCOVERY_ISSUER_URL`, `RBAC_BASE_ENDPOINT`.
- `EnvConfig` auto-copies `.env.sample` to `.env` if missing. Ensure `.env.sample` contains only placeholders.

## ClientBuilder Pattern

- Demonstrate the fluent builder: `new ClientBuilder(endpoint).insecure().build()` for blocking stubs.
- Use `.buildAsync()` for async stubs -- both return `Pair<Stub, ManagedChannel>`.
- `Pair` is `com.nimbusds.jose.util.Pair`. Access stub with `getLeft()`, channel with `getRight()`.
- For authenticated examples, show the full OIDC discovery chain: `OIDCDiscovery` -> `ClientConfigAuth` -> `OAuth2ClientCredentials` -> `ClientBuilder.oauth2ClientAuthenticated(...)`.

## Channel Shutdown (Critical)

- **Blocking stubs**: Always use `try/finally`. RPC calls go in `try`, `channel.shutdown()` goes in `finally`.
- **Async stubs**: Call `channel.shutdown()` in both `onCompleted()` and `onError()` of the `StreamObserver`.
- Never skip channel shutdown -- it leaks threads and file descriptors.
- Use `channel.shutdown()` (graceful), not `channel.shutdownNow()`, unless cancelling in-flight RPCs.

## Error Handling

- Catch `StatusRuntimeException` specifically -- never bare `catch (Exception e)`.
- Print the error and stack trace to stdout/stderr so users can see what failed.
- For bulk responses, iterate over response pairs and check both `hasItem()` and `hasError()` on each pair.
- Bulk errors use `com.google.rpc.Status` (protobuf message), not `io.grpc.Status` (gRPC enum). These are distinct types.

## Import Style

- Use wildcard imports for `v1beta2` generated types: `import org.project_kessel.api.inventory.v1beta2.*;`.
- Use static imports for stub inner classes: `import static ...KesselInventoryServiceGrpc.KesselInventoryServiceBlockingStub;`.
- Use explicit imports for unrelated packages.

## Output

- Print results to stdout so users can see what the API returns.
- Print descriptive messages before output (e.g., `"Check response received successfully:"`).

## Protobuf Request Construction

- Use the generated `.newBuilder()` pattern for all protobuf objects.
- Use `Utils` factory methods from `org.project_kessel.api.rbac.v2` for RBAC resource types when applicable (see `CheckBulkExample`, `ListWorkspacesExample`).
- For streaming, use `Iterator` with `hasNext()`/`next()` for blocking stubs, or `StreamObserver` for async stubs.

## What Not to Put Here

- No unit tests -- those belong in `kessel-sdk/src/test/`.
- No production logic -- examples are for demonstration only.
- No real credentials in committed files. `.env` is gitignored; `.env.sample` has placeholders.
