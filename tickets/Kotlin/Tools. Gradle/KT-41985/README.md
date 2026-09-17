# KT-41985 verification attempt

KT-41985 reports that assigning `freeCompilerArgs` with `=` can silently
discard arguments introduced by higher-level Kotlin Gradle DSL such as
`explicitApi()`. The proposed behavior is an actionable failure that guides
users to append arguments instead.

No project was attached to the issue. A minimal Kotlin/JVM project was created
with `explicitApi()` followed by assignment to `compilerOptions.freeCompilerArgs`.
It was checked in `gradle:9.7.1-jdk25` with Gradle 9.7.1, OpenJDK 25, and the
repository-configured Kotlin version 2.4.20.

Run from `reproducer`:

```sh
docker run --rm --network host -v "$PWD:/workspace" -w /workspace gradle:9.7.1-jdk25 gradle --no-daemon compileKotlin
```

Expected: the build should reach Kotlin compilation, allowing the assignment's
effect on `explicitApi()` compiler arguments to be checked.

Actual: plugin resolution failed before compilation because
`org.jetbrains.kotlin.jvm:org.jetbrains.kotlin.jvm.gradle.plugin:2.4.20` was
not found in the Gradle Plugin Portal.

Conclusion: `FAILED`. The configured Kotlin plugin version must resolve before
this issue can be verified; no runnable reproducer archive was created.
