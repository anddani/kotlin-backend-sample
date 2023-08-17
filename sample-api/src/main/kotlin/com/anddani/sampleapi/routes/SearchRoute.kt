package com.anddani.sampleapi.routes

import com.anddani.common.SearchError
import com.anddani.sampleapi.ApiRoute
import com.anddani.service.smt.SmtService
import com.anddani.service.smt.data.Demon
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.SuspendableResultBinding
import com.github.michaelbull.result.toResultOr
import io.ktor.server.request.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRoute @Inject constructor(
    private val smtService: SmtService
) : ApiRoute<List<Demon>, SearchError> {
    override suspend fun SuspendableResultBinding<SearchError>.route(
        request: ApplicationRequest
    ): Result<List<Demon>, SearchError> {
        val query = request.queryParameters["q"]
            .toResultOr { SearchError.SearchQueryParamMissing }
            .bind()

        return Ok(smtService.searchDemons(query))
    }
}
