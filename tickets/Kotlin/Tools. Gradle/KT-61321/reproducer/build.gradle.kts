plugins {
//    kotlin("jvm") version "1.9.30-dev-731"
    kotlin("jvm") version "1.9.20-Beta-35"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")

}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
    explicitApi()
}