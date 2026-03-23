---
name: release-java-sdk
description: Release a new version of the Kessel Java SDK (kessel-sdk). Guides through version bump, code generation, quality checks, Maven Central publish, git tagging, and GitHub release creation. Use when the user wants to release, publish, bump version, or cut a new release of the Java SDK.
---

# Release Kessel Java SDK

## Prerequisites

- Write access to the GitHub repository
- Maven Central account with publish access to `org.project-kessel`
- Credentials configured for [Maven Central](https://central.sonatype.org/publish/publish-portal-maven/#credentials)
- GPG key for publishing to [Maven Central](https://central.sonatype.org/publish/requirements/gpg/)
- Java 21+
- [buf](https://github.com/bufbuild/buf) for protobuf/gRPC code generation

## Release Process

### Step 1: Determine the Version

Check existing tags to find the current version:

```bash
git fetch --tags
git tag --sort=-v:refname | head -5
```

Or via GitHub:

```bash
gh release list --limit 5
```

Choose the new version following [Semantic Versioning](https://semver.org/):
- **MAJOR**: incompatible API changes
- **MINOR**: backward-compatible new functionality
- **PATCH**: backward-compatible bug fixes

Then set the `VERSION` env var for use in subsequent steps:

```bash
export VERSION=X.Y.Z
echo "Releasing version: v${VERSION}"
```

### Step 2: Update the Version

```bash
./mvnw versions:set -DnewVersion=${VERSION}
```

Verify the root `pom.xml` `<version>` element now contains the concrete version you set (e.g. `1.2.3`).

### Step 3: Update Dependencies (if needed)

Regenerate gRPC code if there are updates to the Kessel Inventory API:

```bash
buf generate
```

### Step 4: Run Quality Checks

```bash
./mvnw clean install

cd examples
../mvnw compile exec:java -Prun-auth
cd ..
```

### Step 5: Build and Publish to Maven Central

Ensure your `settings.xml` has token credentials configured per [Maven Central docs](https://central.sonatype.org/publish/publish-portal-maven/#credentials), and that you have a [GPG key](https://central.sonatype.org/publish/requirements/gpg/) configured.

```bash
./mvnw -B clean deploy -Psign
```

Check the deployment page for errors before publishing on the Maven Central web portal. Verify that both `org.project-kessel:kessel-sdk-parent` and `org.project-kessel:kessel-sdk` are present in the staging repository before closing it.

### Step 6: Commit and Push

The `versions:set` command creates temporary `pom.xml.versionsBackup` files and modifies `pom.xml` version fields. Restore the pom files, then commit any other release changes (e.g. regenerated code):

```bash
./mvnw versions:revert
git add .
git commit -m "chore: bump version to ${VERSION}"
git push origin main
```

### Step 7: Tag the Release

```bash
git tag -a v${VERSION} -m "Release version ${VERSION}"
git push origin v${VERSION}
```

### Step 8: Create GitHub Release

```bash
gh release create v${VERSION} --title "v${VERSION}" --generate-notes
```

Or manually:

- Go to the [GitHub Releases page](https://github.com/project-kessel/kessel-sdk-java/releases)
- Click "Create a new release"
- Select the tag you just created
- Add release notes describing the changes
- Publish the release

## Quick Reference Checklist

```text
Release v${VERSION}:
- [ ] Check existing tags and determine new version
- [ ] Set VERSION env var
- [ ] Update version (./mvnw versions:set -DnewVersion=${VERSION})
- [ ] Regenerate gRPC code if needed (buf generate)
- [ ] Run ./mvnw clean install and test examples
- [ ] Deploy to Maven Central (./mvnw -B clean deploy -Psign)
- [ ] Commit and push changes
- [ ] Create and push git tag (v${VERSION})
- [ ] Create GitHub release
```
