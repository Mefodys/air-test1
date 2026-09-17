# KT-59156 reproducer

This minimal Gradle JVM project recreates the Kotlin source layout from the
issue: a source file under `src/main/kotlin` imports the empty `noSources`
package.

## Checked environment

- Kotlin Gradle plugin: 1.8.21
- Gradle: 7.6.6
- JDK: Eclipse Temurin 17.0.20
- OS: Linux (Docker container)

## Run

```sh
gradle --no-daemon compileKotlin
```

Expected result: Kotlin compilation fails with an unresolved reference for
`noSources`, matching the Gradle/Kotlin case in the report.

Actual result in this automation environment: the isolated Docker container
could not resolve Maven Central, so Gradle could not download
`org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21`. Compilation did not start.

Conclusion: a human or environment with dependency access must run the supplied
project to verify the reported compiler diagnostic.
