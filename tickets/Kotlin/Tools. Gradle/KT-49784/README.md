# KT-49784 reproducer

This minimal Gradle project creates a `common` source set, registers it as a
Java feature, and publishes Gradle module metadata. It compares the attributes
of the main `apiElements` variant with `commonApiElements`.

Checked versions:

- Kotlin Gradle plugin: 2.4.20
- Gradle: 9.7.1
- JDK: Eclipse Temurin 25.0.4 (container runtime); produced bytecode targets JVM 17
- OS: Linux in the `gradle:9.7.1-jdk25` Docker image

Run from the `reproducer` directory:

```sh
docker run --rm --network host \
  -e JAVA_TOOL_OPTIONS='<proxy settings required by this environment>' \
  -v "$PWD:/workspace" -w /workspace gradle:9.7.1-jdk25 sh ./verify.sh
```

Expected result: `commonApiElements` has the same
`org.gradle.jvm.environment` and `org.jetbrains.kotlin.platform.type`
attributes as `apiElements`.

Actual result: `apiElements` contains `standard-jvm` and `jvm` for those
attributes, while `commonApiElements` contains neither. The script prints the
missing attributes and exits with status 1, reproducing the reported problem.

Conclusion: the issue reproduces with the checked current Kotlin and Gradle
versions. The project was created from the issue description; no original
reproducer was attached.
