# KT-41582 reproducer

This project applies Kotlin JVM and `maven-publish`, but intentionally declares
no `publishing.publications` block.

## Checked versions

- Kotlin: 2.4.20
- Gradle: 9.7.1
- JDK: OpenJDK 25.0.2
- OS: Linux, Docker `gradle:9.7.1-jdk25`

## Run

```bash
./gradlew --no-daemon -Dmaven.repo.local="$PWD/.m2" clean publishToMavenLocal
find .m2 -type f
```

## Expected result

Applying `maven-publish` to a Kotlin JVM module should create a publication,
so `publishToMavenLocal` should write its POM and artifacts to the selected
Maven-local repository without extra publication configuration.

## Actual result

The build succeeds, but `publishToMavenLocal` is UP-TO-DATE and no Maven-local
repository or artifacts are created. `tasks --all` lists only the aggregate
`publishToMavenLocal` task and no publication-specific publish task.
