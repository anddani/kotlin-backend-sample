package com.anddani.sampleapi

import com.anddani.sampleapi.dagger.DaggerApplicationComponent
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    println("Starting server...")
    val applicationComponent = DaggerApplicationComponent.builder().build()
    val smtService = applicationComponent.smtService
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                call.respondText("Hello world!")
            }
            get("/search") {
                val query = call.request.queryParameters["q"]
            }
        }
    }.start(wait = true)
}