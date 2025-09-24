# Kessel SDK for Java

A Java SDK for connecting to Kessel services using gRPC with a fluent client builder API.

## Table of Contents

- [Installation](#installation)
- [Examples](#examples)
- [Development](#development)

## Installation

Add to your Maven `pom.xml`:

```xml
<dependency>
    <groupId>org.project-kessel</groupId>
    <artifactId>kessel-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Authentication

The SDK supports OAuth 2.0 Client Credentials flow for authentication with automatic token caching and refresh.

## Examples

Check out the [examples directory](./examples) for working code samples:

- **Auth examples**: OAuth2 Client Credentials flow with token management
- **Builder examples**: Fluent client builder patterns
- **gRPC examples**: Direct gRPC client usage

Run examples:
```bash
./mvnw clean install -Dgpg.skip
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

1. **Update the Version**

```bash
# Edit pyproject.toml
# Update the <version></version> field to the new version number
vim kessel-sdk/pom.xml
```

2. **Update Dependencies (if needed)**

```bash
# Regenerate gRPC code if there are updates to the Kessel Inventory API
buf generate
```

3. **Run Quality Checks**

```bash
# Build the project
./mvnw clean install -Dgpg.skip
# Test that examples can compile without errors
cd examples
../mvnw compile exec:java -Prun-auth
```

4. **Commit and Push Changes**

```bash
# Revert changes to pom.xml
git stash
# Commit any related changes (if any, e.g. proto updates)
git commit -m "chore: bump version to X.Y.Z"
git push origin main # or git push upstream main
```

5. **Build and Publish the Package**

It's required to configure your `settings.xml` with token credentials before deploying.
You can follow the instructions found [here](https://central.sonatype.org/publish/publish-portal-maven/#credentials) for generating a user token and configuring your `setings.xml`.

For publishing it is also required to have a [GPG key](https://central.sonatype.org/publish/requirements/gpg/) configured.

```bash
# Push deployment to maven central
./mvnw -B clean deploy
```
Check deployment page for errors before publishing on maven web portal.

6. **Tag the Release**

```bash
# Create and push a git tag
git tag -a vX.Y.Z -m "Release version X.Y.Z"
git push origin vX.Y.Z
```

7. **Create GitHub Release**

- Go to the [GitHub Releases page](https://github.com/project-kessel/kessel-sdk-py/releases)
- Click "Create a new release"
- Select the tag you just created
- Add release notes describing the changes
- Publish the release

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.
