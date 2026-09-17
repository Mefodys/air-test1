plugins {
    kotlin("jvm") version "2.4.20"
}

repositories {
    mavenCentral()
}

// This mirrors the Kotlin DSL configuration reported in KT-41077. The created
// KotlinSourceSet is not associated with a JVM compilation.
kotlin {
    sourceSets {
        create("grafana") {
            kotlin.srcDir("src/grafana/kotlin")
        }
    }
}
