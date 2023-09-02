package com.anddani.sampleapi

import com.anddani.common.errors.InternalApiError
import com.anddani.common.responses.ListBody
import com.anddani.common.responses.ObjectBody
import com.anddani.common.responses.StringBody
import com.anddani.common.responses.SuccessBody
import com.anddani.sampleapi.dagger.DaggerApplicationComponent
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    println("Creating dagger component...")
    val applicationComponent = DaggerApplicationComponent.builder().build()
    val routes = applicationComponent.routes
    println("Starting server...")
    embeddedServer(Netty, 8080) {
        routing {
            routes.forEach { route: ApiRoute<*, InternalApiError> ->
                when (route.method) {
                    HttpMethod.Get -> get(route.path) {
                        val result = route.go(context.request)
                        val message = result.toMessage()
                        message.respond(call)
                    }

                    else -> throw IllegalArgumentException("Unsupported method ${route.method}")
                }
            }
        }
    }.start(wait = true)
}

private fun Result<Pair<HttpStatusCode, SuccessBody?>, InternalApiError>.toMessage(): Pair<HttpStatusCode, SuccessBody?> =
    when (this) {
        is Ok -> this.value
        is Err -> error.toMessage()
    }

private suspend fun Pair<HttpStatusCode, SuccessBody?>.respond(call: ApplicationCall) {
    val (statusCode: HttpStatusCode, message: SuccessBody?) = this
    if (message != null) {
        call.respond(
            status = statusCode,
            message = when(message) {
                is ListBody -> message.body
                is ObjectBody -> message.body
                is StringBody -> message.body
            }
        )
    } else {
        call.respond(statusCode)
    }
}