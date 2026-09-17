# KT-61410 reproducer verification

This minimal Gradle project asks the Kotlin Gradle DSL for a typed `jsr305`
compiler option, the requested alternative to placing `-Xjsr305=strict` in
`freeCompilerArgs`.

## Checked environment

- Kotlin Gradle plugin: 2.4.20
- Gradle: 9.7.1
- JDK: 25 (Gradle container image)
- OS: Linux container

## Command

```sh
./gradlew compileKotlin --stacktrace
```

Run it in a container with a JDK compatible with Gradle 9.7.1 if isolation is
required.

## Expected result

The Kotlin Gradle DSL should expose a typed compiler option for
`-Xjsr305=strict`, so the build script can configure it outside
`freeCompilerArgs`.

## Actual result

Gradle fails while compiling the build script with `Unresolved reference
'jsr305'` at `compilerOptions { jsr305.set("strict") }`. Kotlin compilation
does not start because the requested typed option is absent.

## Conclusion

REPRODUCER_CREATED. The failure directly demonstrates the reported missing
DSL support with Kotlin Gradle plugin 2.4.20.
