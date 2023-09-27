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
                call.response.status(HttpStatusCode.OK)
                call.response.headers.append("ActiveImage-URL", activeImage)
                call.respond("")
            }

            post {
                val newImageUrl = call.receiveText()
                val headerValue = call.request.headers["ActiveImage-URL"]
                activeImage = if (!headerValue.isNullOrBlank()) headerValue else newImageUrl
                call.respondText("Active Image Updated!", status = HttpStatusCode.OK)
            }
        }
    }
}
