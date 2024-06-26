package com.otienosamwel

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.otienosamwel.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSockets()
    }.start(wait = true)
}
