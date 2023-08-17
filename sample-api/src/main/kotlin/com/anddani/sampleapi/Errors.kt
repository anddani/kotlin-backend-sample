package com.anddani.sampleapi

import com.anddani.common.ApiError
import com.anddani.common.FetchAndPersistDemonError
import com.anddani.common.InternalApiError
import com.anddani.common.SearchError
import io.ktor.http.*

// Here would be a good opportunity to log errors
internal fun InternalApiError.toMessage(): Pair<HttpStatusCode, Any?> = when (this) {
    is FetchAndPersistDemonError.Api -> when (apiError) {
        is ApiError.ClientError -> HttpStatusCode.InternalServerError to null
        is ApiError.FailedToParse -> HttpStatusCode.InternalServerError to null
        is ApiError.ServerError -> HttpStatusCode.FailedDependency to null
        ApiError.UnexpectedError -> HttpStatusCode.InternalServerError to null
    }

    FetchAndPersistDemonError.FailedToPersist -> HttpStatusCode.InternalServerError to ""
    SearchError.SearchQueryParamMissing -> HttpStatusCode.BadRequest to Unit
}
