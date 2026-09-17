plugins {
    kotlin("jvm") version "2.4.20"
}

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()
    compilerOptions {
        // This assignment is the operation reported in KT-41985. It replaces
        // values already present in freeCompilerArgs instead of appending one.
        freeCompilerArgs = listOf("-Xcontext-parameters")
    }
}
