# KT-61321 reproducer verification

The attached `explicit-api-duplication-check.zip` project was preserved as a cleaned copy. A minimal public Kotlin source file was added so that `compileKotlin` would have work to perform; this does not alter the `explicitApi()` configuration under test.

## Checked environment

- Kotlin Gradle plugin: 1.9.20-Beta-35 (declared by the attachment)
- Gradle: 8.2.1
- JDK: Eclipse Temurin OpenJDK 17.0.8
- OS: Linux container

## Command

```sh
gradle compileKotlin --info
```

## Expected result

The compiler invocation should include `-Xexplicit-api=strict` twice after calling `explicitApi()`.

## Actual result

Gradle failed during settings evaluation: the attachment's `org.gradle.toolchains.foojay-resolver-convention` version 0.5.0 could not be resolved from its declared repositories. Kotlin compilation did not begin, so the reported compiler argument duplication could not be observed.

## Conclusion

REPRODUCER_BROKEN. A maintainer needs to provide a resolvable version matrix or an attachment without the unavailable settings plugin before this behavior can be verified.
