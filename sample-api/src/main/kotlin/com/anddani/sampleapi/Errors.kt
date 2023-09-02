package com.anddani.sampleapi

import com.anddani.common.*
import com.anddani.common.errors.FetchAndPersistDemonError
import com.anddani.common.errors.InternalApiError
import com.anddani.common.responses.SuccessBody
import com.anddani.common.responses.toSuccessBody
import io.ktor.http.*

// Here would be a good opportunity to log errors
internal fun InternalApiError.toMessage(): Pair<HttpStatusCode, SuccessBody?> = when (this) {
    is FetchAndPersistDemonError.Api -> when (apiError) {
        is ApiError.ClientError -> HttpStatusCode.InternalServerError to null
        is ApiError.FailedToParse -> HttpStatusCode.InternalServerError to null
        is ApiError.ServerError -> HttpStatusCode.FailedDependency to null
        ApiError.UnexpectedError -> HttpStatusCode.InternalServerError to null
    }

    com.anddani.common.errors.FetchAndPersistDemonError.FailedToPersist -> HttpStatusCode.InternalServerError to null

    com.anddani.common.errors.SearchError.SearchQueryParamMissing -> HttpStatusCode.BadRequest to ApiErrorBody(
        id = "SE1",
        message = "query parameter 'q' missing from request"
    ).toSuccessBody()
}
