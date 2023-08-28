package com.anddani.sampleapi.routes

import com.anddani.common.InternalApiError
import com.anddani.common.JsonSerializable
import com.anddani.common.SearchError
import com.anddani.common.responses.ListBody
import com.anddani.common.responses.toSuccessBody
import com.anddani.sampleapi.ApiRoute
import com.anddani.service.smt.SmtService
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.binding
import com.github.michaelbull.result.toResultOr
import io.ktor.http.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchRoute @Inject constructor(
    private val smtService: SmtService
) : ApiRoute<ListBody, InternalApiError> {

    override val path: String = "/search"
    override val method: HttpMethod = HttpMethod.Get

    override suspend fun go(
        request: ApplicationRequest
    ): Result<Pair<HttpStatusCode, ListBody>, SearchError> = binding {
        val query = request.queryParameters["q"]
            .toResultOr { SearchError.SearchQueryParamMissing }
            .bind()

        val response: List<Demon> = smtService.searchDemons(query)
            .map { demon ->
                Demon(
                    name = demon.name,
                    skills = demon.skills.map { skill ->
                        Demon.Skill(
                            name = skill.name,
                            cost = skill.cost
                        )
                    }
                )
            }

        HttpStatusCode.OK to response.toSuccessBody()
    }
}


@Serializable
data class Demon(
    val name: String,
    val skills: List<Skill>,
) : JsonSerializable {
    @Serializable
    data class Skill(
        val name: String,
        val cost: Int,
    )
}
