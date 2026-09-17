# KT-64210 reproducer verification

This project models the public issue's reported setup: two independent
subprojects apply the Kotlin/JVM plugin without a version, while
`pluginManagement` in `settings.gradle.kts` supplies Kotlin 1.7.0 and uses
`apply false`.

## Checked environment

- Kotlin Gradle plugin: 1.7.0 (the issue's affected version)
- Gradle: 7.6.4
- JDK: Eclipse Temurin 17.0.20
- OS: Linux x86_64 (`eclipse-temurin:17-jdk-jammy` container)

No `versions.yaml` is present in the repository, so the affected Kotlin
version was retained and paired with a compatible Gradle and JDK combination.

## Run

```shell
./gradlew clean build --warning-mode all
```

Expected from the report: the build prints `The Kotlin Gradle plugin was
loaded multiple times in different subprojects` even though neither subproject
declares a plugin version.

Actual: the build succeeds and does not print the reported warning. The exact
reported setup was run twice in an isolated container, including a clean build.

Conclusion: **NOT_REPRODUCIBLE** with the information and affected version
available in the public issue. A later public maintainer comment explains that
subprojects can load separate plugin classpaths, suggesting that additional
classpath differences absent from the reported steps may be required.

Produced by Air Automations. Name: Backlog reproducer / Run: https://air.jetbrains.cloud/org/05cf1a7f-6ab5-713b-abd3-29d0c8a05e2d/automations/532b03ac-789f-4461-97e2-0743d00580e6?run=78e08446-514b-4fde-96ed-052f51cc2ad2
