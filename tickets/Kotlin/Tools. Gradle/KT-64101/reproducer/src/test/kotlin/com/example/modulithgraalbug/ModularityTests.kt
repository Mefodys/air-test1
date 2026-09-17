package com.example.modulithgraalbug

import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules

class ModularityTests {
    var modules: ApplicationModules = ApplicationModules.of(Application::class.java)

    @Test
    fun verifiesModularStructure() {
        modules.verify()
    }
}
