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
    <groupId>org.project_kessel</groupId>
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

This section provides step-by-step instructions for maintainers to release a new version of the Kessel SDK for Node.js.

### Version Management

This project follows [Semantic Versioning 2.0.0](https://semver.org/). Version numbers use the format `MAJOR.MINOR.PATCH`:

- **MAJOR**: Increment for incompatible API changes
- **MINOR**: Increment for backward-compatible functionality additions
- **PATCH**: Increment for backward-compatible bug fixes

**Note**: SDK versions across different languages (Ruby, Python, Go, etc.) do not need to be synchronized. Each language SDK can evolve independently based on its specific requirements and release schedule.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.
