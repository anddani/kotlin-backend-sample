package com.anddani.sampleapi.routes

import com.anddani.common.InternalApiError
import com.anddani.common.SearchError
import com.anddani.sampleapi.ApiRoute
import com.anddani.service.smt.SmtService
import com.anddani.service.smt.data.Demon
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.binding
import com.github.michaelbull.result.toResultOr
import io.ktor.http.*
import io.ktor.server.request.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRoute @Inject constructor(
    private val smtService: SmtService
) : ApiRoute<List<Demon>, InternalApiError> {

    override val path: String = "/search"
    override val method: HttpMethod = HttpMethod.Get

    override suspend fun go(
        request: ApplicationRequest
    ): Result<Pair<HttpStatusCode, List<Demon>>, SearchError> = binding {
        val query = request.queryParameters["q"]
            .toResultOr { SearchError.SearchQueryParamMissing }
            .bind()

        HttpStatusCode.OK to smtService.searchDemons(query)
    }
}
