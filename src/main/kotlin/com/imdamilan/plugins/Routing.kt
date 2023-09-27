package com.imdamilan.plugins

import com.imdamilan.ActiveImage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get {
            call.respond(HttpStatusCode.OK, headersOf("ActiveImage-URL", ActiveImage.current.url))
        }

        post {
            val newImageUrl = call.receiveText()
            val headerValue = call.request.headers["ActiveImage-URL"]
            if (!headerValue.isNullOrBlank())
                ActiveImage.current.url = headerValue
            else
                ActiveImage.current.url = newImageUrl
            call.respond(HttpStatusCode.OK)
        }
    }
}
