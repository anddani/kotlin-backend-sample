package com.sample.client.smt

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json

private const val BaseUrl = "https://raw.githubusercontent.com/"

internal val SmtHttpClient = HttpClient {
    expectSuccess = true
    defaultRequest {
        url(BaseUrl)
    }
    install(ContentNegotiation) {
        // GitHub responds with "text/plain" but content is JSON
        register(ContentType.Text.Plain, KotlinxSerializationConverter(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        ))
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}
