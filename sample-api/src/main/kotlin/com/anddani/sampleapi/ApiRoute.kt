package com.anddani.sampleapi

import com.anddani.common.InternalApiError
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.SuspendableResultBinding
import io.ktor.server.request.*

interface ApiRoute <R, E : InternalApiError> {
    suspend fun SuspendableResultBinding<E>.route(request: ApplicationRequest): Result<R, E>
}
