# Security Guidelines -- kessel-sdk-java

## Scope

Rules for modifying authentication, TLS, credential management, and token handling code in this SDK.

---

## 1. Repository Structure -- Security-Relevant Files

| Path | Purpose |
|------|---------|
| `kessel-sdk/src/main/java/org/project_kessel/api/auth/` | OAuth2 credential management, OIDC discovery, token lifecycle |
| `kessel-sdk/src/main/java/org/project_kessel/api/grpc/OAuth2CallCredentials.java` | gRPC call credentials (Bearer token injection) |
| `kessel-sdk/src/main/java/org/project_kessel/api/inventory/AbstractClientBuilder.java` | Channel security policy enforcement |
| `kessel-sdk/src/main/java/org/project_kessel/api/inventory/v*/ClientBuilder.java` | Service-specific builder subclasses |
| `examples/.env.sample` | Template for environment config (safe to commit) |
| `examples/.env` | **Must never be committed** -- listed in `.gitignore` |

---

## 2. TLS / Channel Security

### Default is TLS
- `AbstractClientBuilder.buildChannel()` defaults to `TlsChannelCredentials.create()` when no `ChannelCredentials` are explicitly set. Do NOT change this default.
- The `insecure()` method sets `InsecureChannelCredentials.create()` and clears `callCredentials` to null.

### Insecure + Auth Guard
- `validateCredentials()` throws `IllegalStateException` if both `callCredentials != null` AND `channelCredentials` is not secure (not `TlsChannelCredentials` or a composite wrapping one).
- **Rule**: Never remove or weaken `validateCredentials()`. Never allow `CallCredentials` to be sent over an insecure channel.
- **Rule**: The `isChannelCredentialsSecure()` method recursively checks `CompositeChannelCredentials` and `ChoiceChannelCredentials`. If new credential types are introduced by gRPC, update this method to handle them.

### insecure() Restrictions
- `insecure()` is intended for local development only. It explicitly nulls out `callCredentials` so tokens cannot leak over plaintext.
- **Rule**: Never modify `insecure()` to preserve or accept `CallCredentials`.

---

## 3. OAuth2 Client Credentials Flow

### Components
1. **`ClientConfigAuth`** -- immutable record holding `clientId`, `clientSecret`, `tokenEndpoint`. All three fields are null-checked via `Objects.requireNonNull` in the compact constructor.
2. **`OAuth2ClientCredentials`** -- manages the token lifecycle (fetch, cache, refresh). Uses Nimbus SDK (`com.nimbusds.oauth2.sdk`).
3. **`OAuth2CallCredentials`** -- adapts `OAuth2ClientCredentials` into a gRPC `CallCredentials` that injects `Bearer <token>` into the `authorization` metadata header.
4. **`OAuth2AuthRequest`** -- adapts `OAuth2ClientCredentials` into an HTTP `AuthRequest` for REST calls (used by RBAC workspace fetching).

### Token Caching
- Token is cached in a `volatile RefreshTokenResponse tokenCache` field.
- Cache validity is checked via `isCacheValid()`: the token is valid if `expiresAt` is more than **5 minutes** in the future (`EXPIRATION_WINDOW = Duration.ofMinutes(5)`).
- **Rule**: Do not reduce the 5-minute expiration window. It provides a buffer against clock skew and network latency.
- **Rule**: If `expires_in` is missing or zero in the OAuth response, it defaults to **1 hour** (`DEFAULT_EXPIRE_IN_SECONDS`). Do not remove this fallback.

### Thread Safety
- Refresh uses a `ReentrantLock` with double-checked locking: checks `isCacheValid()` before and after acquiring the lock.
- **Rule**: Maintain the double-checked locking pattern. Do not replace with `synchronized` without preserving the volatile read optimization.
- **Rule**: `tokenCache` must remain `volatile`.

### Force Refresh
- `getToken(boolean forceRefresh)` allows bypassing cache. When `forceRefresh=true`, both cache checks are skipped.
- **Rule**: Do not add force-refresh to any automatic/retry path without rate-limiting, to avoid token endpoint abuse.

### Error Handling
- `OAuth2Exception` is an **unchecked** (`RuntimeException`) exception. All OAuth failures are wrapped in it.
- In `OAuth2CallCredentials`, failures map to `Status.UNAUTHENTICATED` via `metadataApplier.fail()`.
- **Rule**: Never let raw Nimbus exceptions propagate. Always wrap in `OAuth2Exception`.

---

## 4. OIDC Discovery

- `OIDCDiscovery.fetchOIDCDiscovery(String issuerUrl)` resolves the token endpoint from an OIDC issuer URL using Nimbus `AuthorizationServerMetadata.resolve()`.
- It is a static utility class with a private constructor -- do not instantiate.
- **Rule**: Always validate that the resolved metadata contains a non-null `tokenEndpointURI` before returning.
- **Rule**: Wrap all Nimbus exceptions (`GeneralException`, `ParseException`, `IOException`) in `OAuth2Exception`.

---

## 5. Nimbus OAuth Dependency

- `com.nimbusds:oauth2-oidc-sdk` is declared as `<optional>true</optional>` in `kessel-sdk/pom.xml`.
- Both `OAuth2ClientCredentials` and `OIDCDiscovery` perform runtime class-availability checks via `Class.forName()` and throw `OAuth2Exception` with a descriptive message if Nimbus is missing.
- **Rule**: Maintain the optional dependency model. SDK consumers who do not use OAuth must not be forced to pull Nimbus.
- **Rule**: Any new auth class that uses Nimbus must include the same `Class.forName()` guard.
- The examples module declares Nimbus as a **non-optional** (required) dependency since it needs OAuth.

---

## 6. Credential Storage and .env Files

- `.gitignore` explicitly excludes `.env` files. This is critical.
- `examples/.env.sample` contains placeholder values only -- never real credentials.
- **Rule**: NEVER commit real credentials, client secrets, or bearer tokens to `.env`, `.env.sample`, or any tracked file.
- **Rule**: The `EnvConfig` utility auto-copies `.env.sample` to `.env` if missing. Ensure `.env.sample` only contains placeholder/dummy values.
- **Rule**: If you find real credentials in tracked files, treat it as a security incident: rotate the credentials immediately and scrub from git history.

---

## 7. HTTP Auth for REST Endpoints

- `AuthRequest` is the interface for injecting auth into `HttpRequest.Builder`.
- `OAuth2AuthRequest` implements it by calling `credentials.getToken()` and setting the `authorization` header with `Bearer <token>`.
- `FetchWorkspace` accepts an optional `AuthRequest` and applies it if non-null.
- **Rule**: REST endpoints (like RBAC workspace API) must pass auth via the `AuthRequest` interface, not by manually constructing headers.
- **Rule**: The `x-rh-rbac-org-id` header in `FetchWorkspace` is an identity header, not a secret, but treat org IDs as sensitive PII.

---

## 8. Builder API Security Patterns

### Authentication Modes (in `AbstractClientBuilder`)
| Method | Channel | Call Credentials |
|--------|---------|-----------------|
| `oauth2ClientAuthenticated(creds)` | TLS (default) | OAuth2 Bearer |
| `oauth2ClientAuthenticated(creds, chanCreds)` | Explicit | OAuth2 Bearer |
| `authenticated(callCreds)` | TLS (default) | Custom |
| `authenticated(callCreds, chanCreds)` | Explicit | Custom |
| `authenticated()` | TLS (default) | None |
| `unauthenticated()` | TLS (default) | None |
| `unauthenticated(chanCreds)` | Explicit | None |
| `insecure()` | Plaintext | None (forced) |

- **Rule**: `build()` returns `Pair<Stub, ManagedChannel>`. Callers must `shutdown()` the channel in a `finally` block. All examples follow this pattern.
- **Rule**: New `ClientBuilder` subclasses must extend `AbstractClientBuilder` and only override `newStub()` / `newAsyncStub()`. Security logic stays in the abstract parent.

---

## 9. gRPC Transport Security

- `grpc-netty-shaded` is a **test-only** dependency. The SDK does not bundle a transport.
- The examples use `grpc-okhttp` as their transport.
- **Rule**: The SDK must remain transport-agnostic. Never add a runtime dependency on a specific gRPC transport.
- **Rule**: `grpc-core` handles TLS credential negotiation. Do not implement custom TLS handling.

---

## 10. Rules for Agents Modifying Security Code

1. **Never weaken the insecure channel guard.** The `validateCredentials()` check that prevents auth over insecure channels is a core safety invariant.
2. **Never store tokens in logs.** If adding logging, redact access tokens and client secrets.
3. **Never hardcode credentials.** All credentials must come from `ClientConfigAuth` (constructed at runtime from environment or config).
4. **Preserve the optional Nimbus pattern.** New OAuth features must include `Class.forName()` availability checks.
5. **Maintain token cache thread safety.** The `volatile` + `ReentrantLock` + double-check pattern must not be simplified away.
6. **Test null rejection.** All new auth-related constructors and methods must validate inputs with `Objects.requireNonNull` and have corresponding test cases.
7. **Use `OAuth2Exception` for all auth errors.** Do not introduce new exception types for OAuth/OIDC failures.
8. **Keep TLS as the default.** If `channelCredentials` is null, the builder must default to `TlsChannelCredentials.create()`.
9. **Shut down channels.** Any code that calls `build()` or `buildAsync()` must demonstrate proper `ManagedChannel.shutdown()` in a finally block.
10. **Review `.gitignore` before adding config files.** Any new file that could contain secrets must be gitignored.
