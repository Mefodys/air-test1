plugins {
    kotlin("jvm") version "2.4.20"
}

repositories {
    mavenCentral()
}

kotlin {
    compilerOptions {
        // Simulate a compiler argument contributed earlier by a plugin or script.
        freeCompilerArgs.add("-Xjsr305=strict")

        // The assignment reported in KT-41985 silently removes that argument.
        freeCompilerArgs = listOf("-Xcontext-parameters")
    }
}

tasks.register("verifyFreeCompilerArgsReplacement") {
    doLast {
        val arguments = kotlin.compilerOptions.freeCompilerArgs.get()
        check(arguments == listOf("-Xcontext-parameters")) {
            "Expected assignment to replace the prior argument, but was $arguments"
        }
        println("KT-41985 reproduced: assignment silently replaced the prior freeCompilerArgs value")
    }
}
