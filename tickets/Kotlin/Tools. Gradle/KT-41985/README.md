# KT-41985 reproducer

This project demonstrates that assigning to `compilerOptions.freeCompilerArgs`
can silently discard an argument previously added by a plugin or build script.
It was created from the issue description; the issue has no attachment.

It was checked with Kotlin 2.4.20, Gradle 9.7.1, and JDK 25 in the
`gradle:9.7.1-jdk25` container on Linux.

Run from the `reproducer` directory:

```sh
docker run --rm --network host -v "$PWD:/workspace" -w /workspace gradle:9.7.1-jdk25 gradle --no-daemon verifyFreeCompilerArgsReplacement
```

Expected: replacing an existing `freeCompilerArgs` value with `=` should fail
with an actionable message directing users to append instead.

Actual: the build succeeds without a diagnostic. The verification task observes
only `-Xcontext-parameters`; the earlier `-Xjsr305=strict` value was removed.

Conclusion: the reported unsafe replacement behavior reproduces. The project
uses the current `compilerOptions` DSL and the Kotlin assignment syntax, with
one earlier argument added solely to make the replacement observable.

Produced by Air Automations. Name: Backlog reproducer / Run: https://air.jetbrains.cloud/org/05cf1a7f-6ab5-713b-abd3-29d0c8a05e2d/automations/532b03ac-789f-4461-97e2-0743d00580e6?run=fbc6109b-896b-46bb-a328-8501a251b1f3
