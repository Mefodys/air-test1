# Modulith Graal Bug

This project demonstrates an issue related to the combination of Spring Boot Modulith together with `org.graalvm.buildtools.native` or some other issue.

Current, running the test 'ModularityTests.verifiesModularStructure()' fails.

It no longer fails if:
* ApplicationTests.kt is removed or
* the `org.graalvm.buildtools.native` plugin is commented out in build.gradle.kts
