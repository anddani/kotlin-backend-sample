package com.anddani.sampleapi.routes

import com.anddani.common.InternalApiError
import com.anddani.sampleapi.ApiRoute
import com.anddani.service.smt.SmtService
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.binding
import io.ktor.http.*
import io.ktor.server.request.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncRoute @Inject constructor(
    private val smtService: SmtService,
) : ApiRoute<Unit, InternalApiError> {

    override val path: String = "/sync"
    override val method: HttpMethod = HttpMethod.Get

    override suspend fun go(request: ApplicationRequest): Result<Pair<HttpStatusCode, Unit>, InternalApiError> = binding {
        smtService.fetchAndPersistDemons().bind()
        HttpStatusCode.OK to Unit
    }
}