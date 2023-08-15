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
    val smtApiClient = applicationComponent.smtApiClient
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                call.respondText(smtApiClient.getDemons().toString())
            }
        }
    }.start(wait = true)
}