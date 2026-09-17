# KT-50130 reproducer verification

The issue describes a Kotlin Gradle build where `compileKotlin.destinationDir`
is set to a directory containing a pre-existing file. On the first,
non-incremental compilation, that file is expected to be removed. The issue
comments confirm this behavior is intentional in `KotlinCompile`.

The reported environment is Gradle 7.2 on Fedora 34. This repository does not
contain `versions.yaml`, so no current Kotlin or plugin version is configured.
An isolated Gradle 8.10.2 / JDK 17 container was attempted, but it could not
resolve `org.jetbrains.kotlin.jvm` from the Gradle Plugin Portal, so the task
could not be executed.

Expected: `compileKotlin` leaves unrelated files in its custom destination
directory intact.

Actual: no compilation ran because the Kotlin Gradle plugin was unavailable to
the isolated environment.

Conclusion: `NEEDS_HUMAN`. Provide `versions.yaml` and a reachable repository
for the selected Kotlin Gradle plugin, then verify the documented command:

```sh
./gradlew compileKotlin
```
