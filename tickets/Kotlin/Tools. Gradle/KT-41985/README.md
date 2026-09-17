# KT-41985 reproducer

This minimal project configures `explicitApi()` and then replaces
`kotlinOptions.freeCompilerArgs`. The source intentionally declares a public
function without explicit visibility; retaining the argument contributed by
`explicitApi()` should make that source fail compilation.

The project was prepared for Kotlin 2.4.20, Gradle 9.7.1, and JDK 25, matching
the configuration used by the preceding queue results. The repository has no
`versions.yaml`, so this configuration could not be independently confirmed.

Run from the `reproducer` directory:

```sh
docker run --rm --network host -v "$PWD:/workspace" -w /workspace gradle:9.7.1-jdk25 gradle --no-daemon clean compileKotlin
```

Expected: replacing `freeCompilerArgs` is rejected with an actionable error,
or the `explicitApi()` compiler argument remains and compilation reports the
missing explicit visibility.

Actual: verification could not begin. Gradle could not resolve
`org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:2.4.20` from
the Gradle Plugin Portal or Maven Central. A human must supply the intended
version configuration or an accessible repository containing that plugin.

Conclusion: `NEEDS_HUMAN`; the project is preserved for verification once a
resolvable Kotlin version is available.
