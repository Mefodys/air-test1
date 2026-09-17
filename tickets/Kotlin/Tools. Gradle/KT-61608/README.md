# KT-61608 reproducer verification

This minimal Gradle project registers a custom `KotlinJvmCompile` task and
copies inputs from the standard `compileKotlin` task. The ordinary `sources`
input is accepted, while the reported `scriptSources` and `commonSourceSet`
inputs are not available through the public API.

## Checked environment

- Kotlin Gradle plugin: 2.4.20
- Gradle: 9.1.0
- JDK: 21
- OS: Linux Docker container

## Command

Run from the extracted project directory:

```sh
docker run --rm -v "$PWD:/workspace" -w /workspace gradle:9.1.0-jdk21 gradle help --no-daemon
```

## Expected result

Gradle should configure the custom compile task using supported public APIs,
including the standard task's script and common source inputs.

## Actual result

Kotlin DSL compilation fails with `Unresolved reference 'scriptSources'` and
`Unresolved reference 'commonSourceSet'`. The adjacent public `sources` access
compiles, isolating the failure to the inputs named in the issue.

## Migration changes

- Created a minimal project because the issue has no attached reproducer.
- Used Kotlin 2.4.20, Gradle 9.1.0, and JDK 21.
- Resolved the Kotlin plugin directly from Maven Central because its plugin
  marker was unavailable from the configured plugin portal.

## Conclusion

REPRODUCER_CREATED. The public API gap reported by KT-61608 remains observable
with the checked versions.
