package com.imdamilan.plugins

import com.imdamilan.activeImage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/live") {
            get {
                call.respond(HttpStatusCode.OK, headersOf("ActiveImage-URL", activeImage))
            }

            post {
                val newImageUrl = call.receiveText()
                val headerValue = call.request.headers["ActiveImage-URL"]
                activeImage = if (!headerValue.isNullOrBlank()) headerValue else newImageUrl
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}
