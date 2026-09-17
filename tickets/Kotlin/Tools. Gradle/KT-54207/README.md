# KT-54207 reproducer

This minimal Gradle project contains one Kotlin and one Java source file. It
compares the source JAR written by Gradle's `sourcesJar` task with the source
JAR written by the Kotlin Gradle plugin's `kotlinSourcesJar` task.

Checked versions:

- Kotlin: 2.4.20
- Gradle: 9.7.1
- JDK: Eclipse Temurin 25.0.4
- OS: Linux in the `gradle:9.7.1-jdk25` Docker image

Run from the `reproducer` directory:

```sh
docker run --rm --network host \
  -e JAVA_TOOL_OPTIONS='<proxy settings required by this environment>' \
  -v "$PWD:/workspace" -w /workspace gradle:9.7.1-jdk25 sh ./verify.sh
```

Expected result: `sourcesJar` creates an archive with source files at their
normal paths, while `kotlinSourcesJar` writes those same files below `main/`.

Actual result: `sourcesJar` contained `Kotlin.kt` and `foo/bar/Java.java`;
`kotlinSourcesJar` contained `main/Kotlin.kt` and `main/foo/bar/Java.java`.
The verification script exited successfully after detecting that difference.

Conclusion: the reported duplicate source-JAR tasks and their incompatible
archive layouts reproduce with the checked versions.

Migration changes: no original project was attached. This project was created
from the ticket's source layout and uses Kotlin 2.4.20 with Gradle 9.7.1.
