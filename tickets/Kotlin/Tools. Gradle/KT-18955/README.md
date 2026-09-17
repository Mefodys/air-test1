# KT-18955 reproducer

This project reproduces KT-18955: two Kotlin compilations with the same module
name are merged into one JAR, producing two entries with the same
`META-INF/duplicate-module.kotlin_module` name.

No original reproducer was attached to the issue. This minimal Gradle project
uses `main` and `extra` Kotlin source sets, explicitly assigns both compilations
the same module name, and packages the extra output into the standard JAR.

Verified environment: Kotlin Gradle plugin 2.4.20, Gradle 9.7.1, OpenJDK 25.0.2,
and Linux.

Run:

```sh
./gradlew --no-daemon clean jar
jar tf build/libs/kt-18955-reproducer.jar | grep 'META-INF/duplicate-module.kotlin_module'
```

Expected and observed result: the command prints two identical Kotlin module
metadata paths. The build itself succeeds; the reproduced condition is the
duplicate metadata entry, not a build failure.

The issue requested a diagnostic for this situation. Kotlin 2.4.20 does not
emit such a diagnostic during this reproduction.
