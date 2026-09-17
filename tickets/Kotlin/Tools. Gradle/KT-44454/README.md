# KT-44454 reproducer

This is an updated copy of the linked issue reproducer. It verifies the Kotlin
incremental-compilation case reported in KT-44454. It first compiles a Java module that declares
`requires kotlin.stdlib`, removes that declaration, and compiles Kotlin again.

Checked versions:

- Kotlin: 2.4.20
- Gradle: 9.7.1
- JDK: Eclipse Temurin 25.0.4
- OS: Linux in the `gradle:9.7.1-jdk25` Docker image

Migration changes: upgraded Kotlin from 1.4.21 to 2.4.20, upgraded Gradle from
6.8.1 to 9.7.1, replaced the deprecated `kotlinOptions` DSL with
`compilerOptions`, and added explicit plugin repositories for current Gradle.

Run from the `reproducer` directory:

```sh
docker run --rm --network host \
  -e JAVA_TOOL_OPTIONS='-Dhttp.proxyHost=172.17.0.1 -Dhttp.proxyPort=13128 -Dhttps.proxyHost=172.17.0.1 -Dhttps.proxyPort=13128 -Dhttp.nonProxyHosts=localhost|127.0.0.1' \
  -v "$PWD:/workspace" -w /workspace gradle:9.7.1-jdk25 sh ./verify.sh
```

Expected: the second compilation fails because `module-info.java` no longer
declares `requires kotlin.stdlib`.

Actual: the second `compileKotlin` task succeeds after the module declaration
changes. `verify.sh` exits with status 1 to make that unexpected success
visible.

Conclusion: the reported invalid caching behavior reproduces with the checked
versions.
