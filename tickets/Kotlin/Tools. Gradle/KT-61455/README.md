# KT-61455 reproducer verification

This minimal Kotlin Multiplatform project creates a Linux/Native target and
applies the Gradle Enterprise plugin, matching the configuration in the issue.

## Checked environment

- Kotlin Gradle plugin: 2.4.20
- Gradle Enterprise plugin: 3.18.2
- Gradle: 9.1.0 container image (a 9.7.1 wrapper could not be generated)
- JDK: 21
- OS: Linux Docker container

## Command

```sh
docker run --rm --network=host -v "$PWD:/workspace" -w /workspace gradle:9.1.0-jdk21 gradle clean build --scan --no-daemon
```

## Expected result

A Build Scan should show non-incremental Kotlin/Native tasks without the
`Incremental build` tag or empty source-change lists.

## Actual result

The container could not resolve the Gradle Enterprise plugin through Gradle,
despite the plugin artifact being available from the plugin portal. More
importantly, the required Build Scan data is only available in a configured
Build Scan/Develocity service, which this environment does not have access to.

## Conclusion

NEEDS_HUMAN. A maintainer with access to the relevant Build Scan service must
run the project and inspect the Kotlin/Native task labels and custom values.
