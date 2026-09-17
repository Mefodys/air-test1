# KT-64462 reproducer

This minimal Gradle project implements the public issue's reported sequence:
it creates a custom Kotlin source set named `x`, then creates a Java source set
with the same name.

## Checked environment

- Kotlin Gradle plugin: 2.0.0
- Gradle: 8.2.1
- JDK: Eclipse Temurin 17.0.20
- OS: Linux x86_64 (`eclipse-temurin:17-jdk-jammy` container)

The issue used an unpublished `2.0.255-SNAPSHOT`; Kotlin 2.0.0 is the nearest
published release. The repository does not contain the required `versions.yaml`.

## Run

```shell
./gradlew help --warning-mode all --stacktrace
```

## Expected result

Gradle reports that `xImplementation`, `xCompileOnly`, and `xRuntimeOnly` were
created explicitly even though those names are reserved for Gradle.

## Actual result

Gradle 8.2.1 emitted the reported deprecation warning for each of those three
configurations. The stack traces point to `JavaBasePlugin.warnIfConfigurationAlreadyExists`,
and the `help` task then completed successfully.

Conclusion: **REPRODUCER_CREATED**. The newly created minimal project reproduces
the reported behavior in an isolated container.

Produced by Air Automations. Name: Backlog reproducer / Run: https://air.jetbrains.cloud/org/05cf1a7f-6ab5-713b-abd3-29d0c8a05e2d/automations/532b03ac-789f-4461-97e2-0743d00580e6?run=d5813ab3-3d6d-4d00-b170-591067c8d41e
