package com.otienosamwel.chat

import io.ktor.http.cio.websocket.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import java.util.*

fun Routing.configureChat() {
    val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())
    webSocket("/defaultChat/{userName}") {
        val thisConnection = Connection(this, username = call.parameters["userName"]!!)
        if (connections.all { it.username != thisConnection.username }) connections += thisConnection

        connections.forEach {connection ->
           if(connection.username != thisConnection.username) connection.session.send("${thisConnection.username} joined the chat.")
        }

        try {
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                connections.forEach {
                    if (it.username != thisConnection.username) it.session.send("[x]: ${thisConnection.username} :: ${frame.readText()}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connections -= thisConnection
        }
    }
}

data class Connection(
    val session: DefaultWebSocketServerSession,
    val username: String
)
