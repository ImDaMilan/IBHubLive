package com.imdamilan

import com.imdamilan.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    println("Starting app")
    embeddedServer(Netty, port = 10000, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
    println("Started")
}

fun Application.module() {
    configureRouting()
}

var activeImage = ""