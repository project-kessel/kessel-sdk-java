# Kessel SDK for Java

A Java client SDK for [Project Kessel](https://github.com/project-kessel) services. It provides a fluent builder API over gRPC-generated stubs, with optional OAuth 2.0 Client Credentials authentication, automatic token caching, and RBAC utility helpers.

## Table of Contents

- [Project Structure](#project-structure)
- [Installation](#installation)
- [Authentication](#authentication)
- [Examples](#examples)
- [Development](#development)
- [Prerequisites](#prerequisites)
- [Documentation](#documentation)
- [Release Instructions](#release-instructions)
- [License](#license)


## Project Structure

```
kessel-sdk-java/              # Parent POM (kessel-sdk-parent)
  kessel-sdk/                 # Published SDK artifact (org.project-kessel:kessel-sdk)
    src/main/java/
      org/project_kessel/api/
        auth/                 # OAuth2, OIDC, token management
        grpc/                 # gRPC call credentials adapter
        inventory/            # Client builders and generated service stubs (v1, v1beta1, v1beta2)
        rbac/v2/              # RBAC utilities (workspaces, etc.)
    src/test/java/            # Tests (mirrors source structure)
  examples/                   # Runnable examples (not published)
  docs/                       # Contributor guideline files
```

See [AGENTS.md](AGENTS.md) for the full structure with generated-vs-hand-written code boundaries.

## Installation

Add to your Maven `pom.xml`:

```xml
<dependency>
    <groupId>org.project-kessel</groupId>
    <artifactId>kessel-sdk</artifactId>
    <version>1.6.0</version>
</dependency>
```

### gRPC Transport (required)

The SDK is transport-agnostic -- you must also add a gRPC transport runtime. Pick one:

```xml
<!-- Option A: Netty (most common for server-side / standalone apps) -->
<dependency>
    <groupId>io.grpc</groupId>
    <artifactId>grpc-netty-shaded</artifactId>
    <version>1.77.0</version>
    <scope>runtime</scope>
</dependency>

<!-- Option B: OkHttp (lighter, good for Android or constrained environments) -->
<dependency>
    <groupId>io.grpc</groupId>
    <artifactId>grpc-okhttp</artifactId>
    <version>1.77.0</version>
    <scope>runtime</scope>
</dependency>
```

### Nimbus OAuth (optional)

If you use the SDK's built-in OAuth 2.0 Client Credentials support, add the Nimbus dependency. If you supply your own `CallCredentials` or connect without auth, you can skip this:

```xml
<dependency>
    <groupId>com.nimbusds</groupId>
    <artifactId>oauth2-oidc-sdk</artifactId>
    <version>11.30.1</version>
</dependency>
```

## Authentication

The SDK supports OAuth 2.0 Client Credentials flow for authentication with automatic token caching and refresh.

## Listing Workspaces

The `ListWorkspaces.listWorkspaces()` helper automatically paginates through
all workspaces a subject can access. Continuation tokens are handled internally.

```java
import org.project_kessel.api.rbac.v2.ListWorkspaces;
import org.project_kessel.api.rbac.v2.Utils;

Iterable<StreamedListObjectsResponse> workspaces =
    ListWorkspaces.listWorkspaces(client, Utils.principalSubject("alice", "redhat"), "viewer");

// Lazy iteration (constant memory)
for (StreamedListObjectsResponse response : workspaces) {
    System.out.println(response.getObject().getResourceId());
}

// Materialise into a List
List<StreamedListObjectsResponse> all =
    StreamSupport.stream(workspaces.spliterator(), false)
                 .collect(Collectors.toList());
```

See [`examples/`](./examples) for complete working examples.

## Examples

Check out the [examples directory](./examples) for working code samples:

- **Auth examples**: OAuth2 Client Credentials flow with token management
- **Builder examples**: Fluent client builder patterns
- **gRPC examples**: Direct gRPC client usage

Run examples:
```bash
./mvnw clean install
cd examples
../mvnw compile exec:java -Prun-auth
```

## Development

### Building

```bash
./mvnw clean compile
```

### Testing

```bash
./mvnw test
```

### Installing

```bash
./mvnw clean install
```

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- [buf](https://github.com/bufbuild/buf) for protobuf/gRPC code generation (if contributing)

## Documentation

Detailed guidelines for contributors and AI agents are in the following files:

- [AGENTS.md](AGENTS.md) -- Repository conventions, architecture, naming, and common pitfalls
- [docs/api-contracts-guidelines.md](docs/api-contracts-guidelines.md) -- Protobuf contracts and code generation
- [docs/security-guidelines.md](docs/security-guidelines.md) -- TLS, OAuth2, credential handling
- [docs/integration-guidelines.md](docs/integration-guidelines.md) -- Client builder usage, streaming, RBAC helpers
- [docs/testing-guidelines.md](docs/testing-guidelines.md) -- Test patterns and conventions
- [docs/error-handling-guidelines.md](docs/error-handling-guidelines.md) -- Exception hierarchy and error flows
- [docs/performance-guidelines.md](docs/performance-guidelines.md) -- Concurrency, channel lifecycle, resource cleanup

## Release Instructions

This section provides step-by-step instructions for maintainers to release a new version of the Kessel SDK for Java.

### Version Management

This project follows [Semantic Versioning 2.0.0](https://semver.org/). Version numbers use the format `MAJOR.MINOR.PATCH`:

- **MAJOR**: Increment for incompatible API changes
- **MINOR**: Increment for backward-compatible functionality additions
- **PATCH**: Increment for backward-compatible bug fixes

**Note**: SDK versions across different languages (Ruby, Python, Go, etc.) do not need to be synchronized. Each language SDK can evolve independently based on its specific requirements and release schedule.

### Prerequisites for Release

- Write access to the GitHub repository
- Maven central account with publish access to the `org.project-kessel` namespace
- Credentials configured for [maven central](https://central.sonatype.org/publish/publish-portal-maven/#credentials) 
- GPG key for publishing to [maven central](https://central.sonatype.org/publish/requirements/gpg/)
- Ensure quality checks are passing
- Review and update CHANGELOG or release notes as needed
- Java 21 or higher
- [buf](https://github.com/bufbuild/buf) for protobuf/gRPC code generation:
  ```bash
  # On macOS
  brew install bufbuild/buf/buf
  
  # On Linux
  curl -sSL "https://github.com/bufbuild/buf/releases/latest/download/buf-$(uname -s)-$(uname -m)" -o "/usr/local/bin/buf" && chmod +x "/usr/local/bin/buf"
  ```

### Release Process

1. **Set the VERSION environment variable**

```bash
export VERSION=X.Y.Z
echo "Releasing version: v${VERSION}"
```

2. **Update the Version**

```bash
# Update the project version across all modules
./mvnw versions:set -DnewVersion=${VERSION}

# Verify the root pom.xml <version> element now shows the concrete version (e.g. 1.2.3)
```

3. **Update Dependencies (if needed)**

```bash
# Regenerate gRPC code if there are updates to the Kessel Inventory API
buf generate
```

4. **Run Quality Checks**

```bash
# Build the project
./mvnw clean install
# Test that examples can compile without errors
cd examples
../mvnw compile exec:java -Prun-auth
```

5. **Build and Publish the Package**

It's required to configure your `settings.xml` with token credentials before deploying.
You can follow the instructions found [here](https://central.sonatype.org/publish/publish-portal-maven/#credentials) for generating a user token and configuring your `setings.xml`.

For publishing it is also required to have a [GPG key](https://central.sonatype.org/publish/requirements/gpg/) configured.

```bash
# Exit back to main directory
cd ..
# Push deployment to maven central
./mvnw -B clean deploy -Psign
```
Check deployment page for errors before publishing on maven web portal. Verify that both `org.project-kessel:kessel-sdk-parent` and `org.project-kessel:kessel-sdk` are present in the staging repository before closing it.

6. **Commit and Push Changes**

```bash
# Revert the temporary pom.xml version changes created by versions:set
./mvnw versions:revert
# Commit any related changes (if any, e.g. proto updates)
git add .
git commit -m "chore: bump version to ${VERSION}"
git push origin main # or git push upstream main
```

7. **Tag the Release**

```bash
# Create and push a git tag
git tag -a v${VERSION} -m "Release version ${VERSION}"
git push origin v${VERSION}
```

8. **Create GitHub Release**

```bash
gh release create v${VERSION} --title "v${VERSION}" --generate-notes
```

Or manually:

- Go to the [GitHub Releases page](https://github.com/project-kessel/kessel-sdk-java/releases)
- Click "Create a new release"
- Select the tag you just created
- Add release notes describing the changes
- Publish the release

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.
