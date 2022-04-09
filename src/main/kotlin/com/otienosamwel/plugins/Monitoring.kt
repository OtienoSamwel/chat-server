package com.otienosamwel.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.*
import org.slf4j.event.Level

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
    }
}
