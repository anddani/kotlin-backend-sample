package com.anddani.common

sealed interface InternalApiError

sealed class FetchAndPersistDemonError : InternalApiError {
    data class Api(val apiError: ApiError<GithubErrorBody>) : FetchAndPersistDemonError()
    object FailedToPersist : FetchAndPersistDemonError()
}

sealed class SearchError : InternalApiError {
    object SearchQueryParamMissing : SearchError()
}
