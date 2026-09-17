# KT-64101 reproducer verification

This directory contains a clean copy of the public reproducer at commit
`23f9ed67b1fe775ca210432923506fba2e8cfc1d`.

## Checked environment

- Kotlin JVM and Spring plugins: 1.9.20
- Gradle: 8.4 (wrapper)
- Spring Boot: 3.2.0
- Spring dependency management plugin: 1.1.4
- GraalVM build tools plugin: 0.9.28
- Spring Modulith: 1.1.0
- JDK: Eclipse Temurin 17.0.20
- OS: Linux x86_64 in an `eclipse-temurin:17-jdk-jammy` Docker container

The repository does not contain the required `versions.yaml`, so no configured
version migration could be applied. The reproducer already uses the issue's
affected Kotlin 1.9.20 version and was otherwise preserved unchanged.

## Run

From the extracted archive:

```shell
cd reproducer
./gradlew clean build --stacktrace
```

## Expected and actual result

Expected: Spring Boot's AOT-generated Java sources compile with the transitive
runtime dependency `spring-modulith-core` available on `aotCompileClasspath`.

Actual: `:compileAotJava` fails because
`ApplicationModuleInitializerRuntimeVerification` cannot be resolved in the
generated `ApplicationModuleInitializerRuntimeVerification__BeanDefinitions`
source. The two `cannot find symbol` diagnostics match the public report.

Conclusion: **VERIFIED** with the reproducer's declared historical versions.
