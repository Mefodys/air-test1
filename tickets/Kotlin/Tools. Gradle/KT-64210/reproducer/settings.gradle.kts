pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        kotlin("jvm") version "1.7.0" apply false
    }
}

rootProject.name = "kt-64210-reproducer"
include("module-a", "module-b")
