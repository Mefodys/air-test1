# KT-61986 reproducer verification

This minimal project checks whether `-Xjdk-release=17` also sets Kotlin's JVM
target when the Java compilation uses release 17 and the project toolchain is
JDK 26.

## Checked environment

- Kotlin Gradle plugin: 2.4.20
- Gradle: 9.7.1
- JDK: Temurin 26.0.2
- OS: Linux x86_64 (Docker container)

The required `versions.yaml` is absent from the repository. This uses the same
fallback version matrix as the immediately preceding automation result.

## Command

Run from the extracted project directory:

```sh
docker run --rm \
  -e HTTP_PROXY -e HTTPS_PROXY -e http_proxy -e https_proxy \
  -e JAVA_TOOL_OPTIONS \
  -v "$PWD:/workspace" -w /workspace \
  gradle:9.7.1-jdk26 \
  gradle clean build --warning-mode=all --no-daemon
```

## Expected result

`-Xjdk-release=17` should set the Kotlin JVM target to 17, matching the Java
compile task, so the build should pass JVM target validation.

## Actual result

The build fails in `compileKotlin`. Kotlin reports that `compileJava` targets
17 while `compileKotlin` targets 26. This matches the issue's reported symptom.

## Migration changes

- Created a minimal project because the issue has no attached reproducer.
- Updated Kotlin 1.9.10, Gradle 8.3, and JDK 20 to Kotlin 2.4.20, Gradle 9.7.1,
  and JDK 26.
- Preserved release 17 and omitted the explicit `jvmTarget` workaround.

## Conclusion

REPRODUCER_CREATED. The reported behavior still reproduces with the checked
version matrix.
