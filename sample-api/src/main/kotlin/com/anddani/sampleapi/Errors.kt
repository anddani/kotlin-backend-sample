package com.anddani.sampleapi

import com.anddani.common.*
import io.ktor.http.*

// Here would be a good opportunity to log errors
internal fun InternalApiError.toMessage(): Pair<HttpStatusCode, JsonSerializable?> = when (this) {
    is FetchAndPersistDemonError.Api -> when (apiError) {
        is ApiError.ClientError -> HttpStatusCode.InternalServerError to null
        is ApiError.FailedToParse -> HttpStatusCode.InternalServerError to null
        is ApiError.ServerError -> HttpStatusCode.FailedDependency to null
        ApiError.UnexpectedError -> HttpStatusCode.InternalServerError to null
    }

    FetchAndPersistDemonError.FailedToPersist -> HttpStatusCode.InternalServerError to null

    SearchError.SearchQueryParamMissing -> HttpStatusCode.BadRequest to ApiErrorBody(
        id = "SE1",
        message = "query parameter 'q' missing from request"
    )
}
