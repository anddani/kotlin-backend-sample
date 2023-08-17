package com.anddani.sampleapi

import com.anddani.common.InternalApiError
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

private fun  Result<Pair<HttpStatusCode, Any?>, InternalApiError>.toMessage(): Pair<HttpStatusCode, Any?> =
    when (this) {
        is Ok -> this.value
        is Err -> error.toMessage()
    }

private suspend fun Pair<HttpStatusCode, Any?>.respond(call: ApplicationCall) {
    val (statusCode: HttpStatusCode, message: Any?) = this
    if (message != null) {
        call.respond(statusCode, message)
    } else {
        call.respond(statusCode)
    }
}