package com.anddani.sampleapi

import com.anddani.sampleapi.dagger.DaggerApplicationComponent
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    println("Starting server...")
    val applicationComponent = DaggerApplicationComponent.builder().build()
    val routes = applicationComponent.routes
    val smtService = applicationComponent.smtService
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                call.respondText("Hello world!")
            }
            get("/sync") {
                smtService.fetchAndPersistDemons()
            }
        }
    }.start(wait = true)
}