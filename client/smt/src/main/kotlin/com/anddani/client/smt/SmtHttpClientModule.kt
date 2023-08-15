package com.anddani.client.smt

import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

private const val BaseUrl = "https://raw.githubusercontent.com/"

@Module
object SmtHttpClientModule {

    @Provides
    @Singleton
    fun provideSmtHttpClient(): HttpClient = HttpClient {
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
}