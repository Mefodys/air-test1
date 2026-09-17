# KT-61619 reproducer verification

This project is a minimal probe for the duplicate fallback warnings reported
when Kotlin receives a JVM toolchain version newer than the compiler supports.

## Checked environment

- Kotlin Gradle plugin: 2.4.20
- Gradle: 9.7.1
- JDK: Temurin 26.0.2
- OS: Linux Docker container

The repository does not contain the required `versions.yaml`, so this check
uses the newest versions available from the configured repositories.

## Command

Run from the extracted project directory:

```sh
docker run --rm \
  -e HTTP_PROXY -e HTTPS_PROXY -e http_proxy -e https_proxy \
  -e JAVA_TOOL_OPTIONS \
  -v "$PWD:/workspace" -w /workspace \
  gradle:9.7.1-jdk26 \
  gradle clean build --warning-mode=all --no-daemon
```

The proxy variables are needed only in environments that require a proxy to
download dependencies.

## Expected result

With an installed JVM toolchain newer than Kotlin supports, the build should
emit one concise fallback warning rather than duplicate configuration-phase
warnings plus one warning per Kotlin compile task.

## Actual result

The project builds successfully and emits no fallback warning because Kotlin
2.4.20 supports the newest available JDK 26 target. A JDK newer than the
compiler supports was not available, so the essential condition could not be
tested.

## Migration changes

- Created a minimal project because the issue has no attached reproducer.
- Updated the historical Kotlin 1.9.20-Beta/JDK 22 scenario to Kotlin 2.4.20,
  Gradle 9.7.1, and JDK 26.
- Kept the original `jvmToolchain` configuration as the only behavior under
  test.

## Conclusion

NEEDS_HUMAN. Re-run with the configured version matrix and an installed JDK
toolchain newer than that Kotlin compiler supports.
