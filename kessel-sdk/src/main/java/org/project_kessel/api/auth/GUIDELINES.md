# Auth Package Guidelines

Rules for the `org.project_kessel.api.auth` package -- OAuth2 token management, OIDC discovery, and credential data carriers.

## Package Inventory

All files in this package are **hand-written** (not generated). They form the SDK's authentication layer.

| File | Role |
|------|------|
| `AuthRequest` | Interface for injecting auth into `HttpRequest.Builder` (REST calls) |
| `OAuth2AuthRequest` | Implements `AuthRequest` using `OAuth2ClientCredentials` Bearer token |
| `OAuth2ClientCredentials` | Token lifecycle: fetch, cache, thread-safe refresh via Nimbus SDK |
| `ClientConfigAuth` | Immutable record holding `clientId`, `clientSecret`, `tokenEndpoint` |
| `RefreshTokenResponse` | Immutable record holding `accessToken` and `expiresAt` |
| `OIDCDiscovery` | Static utility to resolve token endpoint from OIDC issuer URL |
| `OIDCDiscoveryMetadata` | Immutable record wrapping the discovered `tokenEndpoint` |
| `OAuth2Exception` | Single custom unchecked exception for all auth failures |

## Nimbus Dependency Guards

- Nimbus (`com.nimbusds:oauth2-oidc-sdk`) is declared `<optional>true</optional>` in `kessel-sdk/pom.xml`. It must stay optional.
- Both `OAuth2ClientCredentials` and `OIDCDiscovery` call `Class.forName()` as the first operation in their constructor/entry point. If Nimbus is absent, they throw `OAuth2Exception` with a message naming the required Maven artifact.
- Any new class that imports Nimbus types must include the same `Class.forName()` guard pattern. Never let `NoClassDefFoundError` reach callers.
- `OAuth2ClientCredentials` uses wildcard imports for Nimbus types: `import com.nimbusds.oauth2.sdk.*;`. `OIDCDiscovery` uses specific imports (e.g., `import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;`).

## Record Conventions

- `ClientConfigAuth`, `RefreshTokenResponse`, and `OIDCDiscoveryMetadata` are Java records.
- Every record field is validated via `Objects.requireNonNull` with a descriptive message in the compact constructor.
- Records are immutable and thread-safe by design. They can be freely cached and shared across threads.
- Do not convert these to classes -- the record form enforces immutability.

## Token Caching and Thread Safety

- `tokenCache` is `volatile`. Do not remove the `volatile` qualifier.
- `refreshLock` is a `ReentrantLock`. Do not replace with `synchronized`.
- An `AtomicLong` generation counter prevents thundering herd: concurrent callers coalesce into one SSO request per refresh cycle.
- `isCacheValid()` uses a 5-minute `EXPIRATION_WINDOW` before actual expiry. Do not reduce this window.
- If `expires_in` is missing or zero from the OAuth response, it defaults to 1 hour (`DEFAULT_EXPIRE_IN_SECONDS`). Do not remove this fallback.
- `getToken(boolean forceRefresh)` with `forceRefresh=true` still coalesces via the generation counter. Do not add force-refresh to automatic retry paths without rate-limiting.

## Error Handling

- `OAuth2Exception` extends `RuntimeException`. It is the only custom exception in the auth package.
- All Nimbus exceptions (`ParseException`, `IOException`, `GeneralException`, `ClassNotFoundException`) must be wrapped in `OAuth2Exception`. Never let raw Nimbus exceptions propagate to callers.
- Constructors: `(String message)` and `(String message, Throwable cause)`. Do not add new exception types for auth failures.

## OIDC Discovery

- `OIDCDiscovery` is a static utility class with a private constructor. Do not instantiate it.
- `fetchOIDCDiscovery(String issuerUrl)` validates that the resolved metadata contains a non-null `tokenEndpointURI` before returning.
- Cache the returned `OIDCDiscoveryMetadata` -- the token endpoint does not change at runtime. Call discovery once at startup.

## OAuth2AuthRequest (REST Auth)

- Implements the `AuthRequest` interface for non-gRPC HTTP calls (used by `FetchWorkspace` in the `rbac.v2` package).
- Injects `Bearer <token>` into the `authorization` header via `HttpRequest.Builder`.
- REST endpoints must pass auth via `AuthRequest`, not by manually constructing headers.

## Relationship to gRPC Layer

- `OAuth2CallCredentials` (in the `grpc` package, not this package) wraps `OAuth2ClientCredentials` into gRPC `CallCredentials`.
- Auth failures in the gRPC path surface as `Status.UNAUTHENTICATED` via `metadataApplier.fail()`. This translation happens only in `OAuth2CallCredentials` -- do not add additional translation layers.

## Testing

- Tests live in `kessel-sdk/src/test/java/org/project_kessel/api/auth/`.
- Test class naming: `<ClassName>Test` (e.g., `OAuth2ClientCredentialsTest`).
- Test null rejection with `assertThrows(NullPointerException.class, ...)` for every `requireNonNull` call.
- Concurrency tests use `OAuth2ClientCredentialsConcurrencyTest` with a JDK `HttpServer` mock, `CountDownLatch` barrier, and `AtomicInteger` call counter.
- Use JUnit Jupiter assertions only -- no AssertJ or Hamcrest.
