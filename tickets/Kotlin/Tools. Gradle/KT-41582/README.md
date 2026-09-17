# KT-41582 reproducer

This minimal project compares an unconfigured Kotlin/JVM module with an
unconfigured Kotlin Multiplatform module. Both apply `maven-publish`, but
neither declares a `publishing { publications { ... } }` block.

It was checked with Kotlin 2.4.20, Gradle 9.7.1, and JDK 25 in the
`gradle:9.7.1-jdk25` container.

Run from the `reproducer` directory:

```sh
docker run --rm --network host -v "$PWD:/workspace" -w /workspace gradle:9.7.1-jdk25 gradle --no-daemon :jvm:publishToMavenLocal :mpp:publishToMavenLocal
```

Expected: both modules publish Maven artifacts without extra publication
configuration, as the multiplatform module does.

Actual: `:jvm:publishToMavenLocal` succeeds with no publication tasks and no
JVM artifacts. The multiplatform module runs publication tasks and produces
its Maven artifacts. Kotlin 2.4.20 rejects the legacy standalone Kotlin/JS
plugin, so the reproducer isolates the independently reported JVM behavior.
