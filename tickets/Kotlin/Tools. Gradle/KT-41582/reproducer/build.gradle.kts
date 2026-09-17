plugins {
    kotlin("jvm") version "2.4.20" apply false
    kotlin("js") version "2.4.20" apply false
    kotlin("multiplatform") version "2.4.20" apply false
}

allprojects {
    group = "org.example.kt41582"
    version = "1.0"
    repositories {
        mavenCentral()
    }
}
