# RBAC v2 Package Guidelines

Rules for `org.project_kessel.api.rbac.v2` -- hand-written RBAC utility helpers that build on top of `v1beta2` protobuf types.

## Package Inventory

All files are hand-written. This package provides higher-level abstractions over the gRPC inventory service.

| File | Role |
|------|------|
| `Utils` | Static factory methods for `ResourceReference`, `SubjectReference`, `RepresentationType` |
| `FetchWorkspace` | HTTP client for the RBAC REST API (not gRPC) |
| `ListWorkspaces` | Pagination-aware iterator over `StreamedListObjects` for workspace listing |
| `Workspace` | Jackson-annotated POJO for workspace JSON responses |

## Utils -- Factory Methods

- Static utility class. Use these helpers instead of manually constructing `ResourceReference`/`SubjectReference` for RBAC resource types.
- Reporter type is always `"rbac"` (the `REPORTER_TYPE` constant).
- Resource types: `"workspace"`, `"role"`, `"principal"`.
- `principalSubject(id, domain)` formats the resource ID as `domain/id`. This format is intentional.
- `subject(resourceRef, relation)` handles null/empty relation gracefully -- it only sets the relation if non-null and non-empty.
- When adding new RBAC resource types, follow the same pattern: add a constant, a `*Type()` method returning `RepresentationType`, and a `*Resource(id)` method returning `ResourceReference`.

## FetchWorkspace -- REST Client

- Uses Java `HttpClient` (not gRPC) to call the RBAC workspace REST API at `/api/rbac/v2/workspaces/`.
- Accepts an optional `AuthRequest` for Bearer token injection and an optional `HttpClient` for testability.
- The `x-rh-rbac-org-id` header is set on every request. Treat org IDs as sensitive PII.
- Throws `IOException` for non-200 status codes and unexpected response shapes (expects exactly 1 workspace in `data` array).
- Throws `OAuth2Exception` if token acquisition fails during `auth.configureRequest()`.
- Uses `JsonMapper.instance()` (from `org.project_kessel.api.common`) for the shared `ObjectMapper`. Do not create new `ObjectMapper` instances.
- `@JsonIgnoreProperties(ignoreUnknown = true)` on `Workspace` ensures forward compatibility with new JSON fields.

## Workspace POJO

- `Workspace` is a mutable POJO (not a record) because Jackson needs setters for deserialization.
- Setters are **package-private** (no `public` modifier) -- they are for Jackson's use, not public API.
- Getters are `public`.
- Fields: `id`, `name`, `type`, `description` -- all annotated with `@JsonProperty`.
- Do not convert to a record unless Jackson deserialization is reworked.

## ListWorkspaces -- Paginated Iterator

- Returns `Iterable<StreamedListObjectsResponse>` -- each call to `iterator()` creates a new `WorkspaceListIterator`.
- Pagination is automatic: uses `RequestPagination` with `limit=1000` and continuation tokens.
- Iteration is lazy (constant memory). To materialize, use `StreamSupport.stream(workspaces.spliterator(), false).collect(Collectors.toList())`.
- Stops when `continuationToken` is empty string (not null -- empty string signals final page).
- Errors during iteration are wrapped in `RuntimeException("Error fetching workspaces: ...")`. They surface on `hasNext()`/`next()`, not on `listWorkspaces()`.
- Uses `Utils.workspaceType()` internally for the `objectType` field.
- Accepts optional `Consistency` preferences applied to each paginated request.

## Jackson Usage

- The shared `ObjectMapper` singleton lives in `org.project_kessel.api.common.JsonMapper`. Always use `JsonMapper.instance()`.
- Jackson (`jackson-databind`) is a runtime (not optional) dependency of the SDK. It is required for this package.
- Use `@JsonIgnoreProperties(ignoreUnknown = true)` on all POJOs to handle future API field additions gracefully.
- Use `@JsonProperty` annotations on fields for explicit JSON-to-Java mapping.

## Error Handling

- `FetchWorkspace` is the only SDK surface that uses **checked exceptions** (`IOException`, `InterruptedException`) for error propagation.
- `ListWorkspaces` wraps all errors in unchecked `RuntimeException`. Inspect the cause for `StatusRuntimeException` if gRPC status details are needed.
- `NoSuchElementException` is thrown by the iterator if `next()` is called past the end. Always use `hasNext()` or for-each loops.

## Testing

- Tests live in `kessel-sdk/src/test/java/org/project_kessel/api/rbac/v2/`.
- `FetchWorkspaceTest`: mocks `HttpClient` and `HttpResponse` with `lenient()` in `@BeforeEach`. Uses `@SuppressWarnings("unchecked")` for generic response mocking. JSON test data uses text block `static final String` constants.
- `ListWorkspacesTest`: mocks `KesselInventoryServiceBlockingStub` directly. Returns `Iterator<StreamedListObjectsResponse>` built from `List.of(...).iterator()`. Uses `@Captor` to verify pagination request parameters.
- `UtilsTest`: no mocking needed -- tests static factory method return values. No `@ExtendWith(MockitoExtension.class)`.
