package com.sample.client.smt

import com.sample.client.smt.data.RemoteDemon
import com.github.michaelbull.result.Result
import io.ktor.client.*
import io.ktor.client.request.*

class SmtApiClient {
    private val ktor: HttpClient = SmtHttpClient

    suspend fun getDemons(): Result<Map<String, RemoteDemon>, ApiError<Any?>> = wrapApiCallToResult {
        ktor.get("aqiu384/megaten-fusion-tool/master/src/app/smt4/data/demon-data.json")
    }
}

sealed interface ApiError <T> {
    object UnexpectedError : ApiError<Nothing>
    data class ServerError(
        val statusCode: Int,
        val statusMessage: String,
    ) : ApiError<Nothing>
    data class ClientError<T>(
        val statusCode: Int,
        val statusMessage: String,
        val body: T?,
    ) : ApiError<T>
}
