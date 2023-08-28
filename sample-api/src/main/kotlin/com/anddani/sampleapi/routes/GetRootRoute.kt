package com.anddani.sampleapi.routes

import com.anddani.common.responses.SuccessBody
import com.anddani.common.responses.toSuccessBody
import com.anddani.sampleapi.ApiRoute
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.http.*
import io.ktor.server.request.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRootRoute @Inject constructor(
) : ApiRoute<SuccessBody, Nothing> {

    override val path: String = "/"
    override val method: HttpMethod = HttpMethod.Get

    override suspend fun go(
        request: ApplicationRequest
    ): Result<Pair<HttpStatusCode, SuccessBody>, Nothing> {
        return Ok(HttpStatusCode.OK to "Hello, World!".toSuccessBody())
    }
}
