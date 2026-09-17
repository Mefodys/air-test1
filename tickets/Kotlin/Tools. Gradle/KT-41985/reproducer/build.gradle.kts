import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.4.20"
}

kotlin {
    explicitApi()
}

tasks.withType<KotlinCompile>().configureEach {
    // This assignment replaces arguments contributed by higher-level KGP DSL.
    kotlinOptions.freeCompilerArgs = listOf("-Xcontext-parameters")
}
