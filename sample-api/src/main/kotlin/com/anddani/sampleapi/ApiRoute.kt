package com.anddani.sampleapi

import com.anddani.common.InternalApiError
import com.github.michaelbull.result.Result
import io.ktor.http.*
import io.ktor.server.request.*

interface ApiRoute <out R : Any, out E : InternalApiError> {
    val path: String
    val method: HttpMethod
    suspend fun go(request: ApplicationRequest): Result<Pair<HttpStatusCode, R>, E>
}
