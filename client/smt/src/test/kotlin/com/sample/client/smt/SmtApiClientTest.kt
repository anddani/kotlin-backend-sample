package com.sample.client.smt

import com.sample.client.smt.data.RemoteDemon
import com.sample.common.RemoteErrorBody
import com.sample.common.ApiError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.toResultOr
import com.google.common.truth.Truth.assertThat
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Test

class SmtApiClientTest {

    @Test
    fun `successfully parse empty response from getDemons`() = runTest {
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel("""{}"""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val apiClient = SmtApiClientImpl(mockEngine.toHttpClient())
        assertThat(apiClient.getDemons())
            .isEqualTo(Ok<Map<String, RemoteDemon>>(emptyMap()))
    }

    @Test
    fun `successfully parse response with data from getDemons`() = runTest {
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel("""{"Abaddon":{"lvl":64,"race":"Tyrant","resists":"--r-w-nn","skills":{"Repel Ice":66,"Retaliate":0},"stats":[700,139,87,77,60,70,57]},"Anzu":{"lvl":53,"race":"Raptor","resists":"----dwn-","skills":{"Me Patra":54,"Zionga":0},"stats":[596,118,73,65,51,59,48]}}""".trimIndent()),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val apiClient = SmtApiClientImpl(mockEngine.toHttpClient())
        assertThat(apiClient.getDemons())
            .isEqualTo(
                mapOf(
                    "Abaddon" to RemoteDemon(
                        skills = mapOf(
                            "Repel Ice" to 66,
                            "Retaliate" to 0,
                        )
                    ),
                    "Anzu" to RemoteDemon(
                        skills = mapOf(
                            "Zionga" to 0,
                            "Me Patra" to 54,
                        )
                    )
                )
                    .toResultOr { }
            )
    }

    @Test
    fun `fail to parse successful response from getDemons`() = runTest {
        val mockEngine = MockEngine {
            respond(
                content = ByteReadChannel("""{"Abaddon":{"lvl":64,"race":"Tyrant","resists":"--r-w-nn","stats":[700,139,87,77,60,70,57]}}""".trimIndent()),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val apiClient = SmtApiClientImpl(mockEngine.toHttpClient())
        assertThat(apiClient.getDemons())
            .isEqualTo(Err(ApiError.FailedToParse(HttpStatusCode.OK.value)))
    }

    @Test
    fun `handle internal server error from getDemons`() = runTest {
        val mockEngine = MockEngine {
            respondError(HttpStatusCode.InternalServerError)
        }

        val apiClient = SmtApiClientImpl(mockEngine.toHttpClient())
        assertThat(apiClient.getDemons())
            .isEqualTo(Err(ApiError.ServerError(
                statusCode = HttpStatusCode.InternalServerError.value,
                statusMessage = HttpStatusCode.InternalServerError.description
            )))
    }

    @Test
    fun `handle client error with body from getDemons`() = runTest {
        val mockEngine = MockEngine {
            respond(
                status = HttpStatusCode.BadRequest,
                content = ByteReadChannel("""{"errorId":17}"""),
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val apiClient = SmtApiClientImpl(mockEngine.toHttpClient())
        assertThat(apiClient.getDemons())
            .isEqualTo(Err(ApiError.ClientError(
                statusCode = HttpStatusCode.BadRequest.value,
                statusMessage = HttpStatusCode.BadRequest.description,
                body = RemoteErrorBody(
                    errorId = 17,
                ),
            )))
    }

    @Test
    fun `handle client error without body from getDemons`() = runTest {
        val mockEngine = MockEngine {
            respondError(HttpStatusCode.BadRequest)
        }

        val apiClient = SmtApiClientImpl(mockEngine.toHttpClient())
        assertThat(apiClient.getDemons())
            .isEqualTo(Err(ApiError.ClientError<Any?>(
                statusCode = HttpStatusCode.BadRequest.value,
                statusMessage = HttpStatusCode.BadRequest.description,
                body = null,
            )))
    }
}

private fun MockEngine.toHttpClient(): HttpClient = HttpClient(this) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}
