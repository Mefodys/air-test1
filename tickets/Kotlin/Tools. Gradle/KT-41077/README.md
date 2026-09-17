# KT-41077 reproducer

This project mirrors the Kotlin DSL configuration from KT-41077 using Kotlin
2.4.20, Gradle 9.7.1, and JDK 25.0.2.

Run it in the supplied isolated Gradle container:

```sh
docker run --rm -v "$PWD:/workspace" -w /workspace gradle:9.7.1-jdk25 ./gradlew --no-daemon build
```

The source file in `src/grafana/kotlin` contains an unresolved type and must
fail if it is compiled. The observed `build` result is successful and reports
`:compileKotlin NO-SOURCE`, demonstrating that `kotlin.sourceSets.create` does
not connect the new source set to the JVM compilation.

The ticket's separate expected behavior—an IntelliJ IDEA source-root prompt and
bold source root—requires IntelliJ IDEA 2020.2 on Windows and was not checked
in this Linux environment.
