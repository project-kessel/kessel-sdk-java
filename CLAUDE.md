# CLAUDE.md

@AGENTS.md

## Build and Test Commands

Before any commit or PR:

```bash
./mvnw clean verify
```

This single command builds, tests, and validates the entire multi-module project (kessel-sdk + examples).

**Do not use system `mvn`** -- always use the Maven wrapper `./mvnw` (pinned to Maven 3.9.6).

## Pre-Commit Hook

This repository has an `rh-multi-pre-commit` hook installed at `.git/hooks/pre-commit`. It will run automatically on commit.

## Release Process

Use the `/release-java-sdk` cursor skill when releasing a new version. The skill is located at `.cursor/skills/release-java-sdk/SKILL.md` and covers the full Maven Central publish workflow.
