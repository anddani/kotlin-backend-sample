package com.anddani.common.errors

import com.anddani.common.ApiError
import com.anddani.common.GithubErrorBody

sealed class FetchAndPersistDemonError : InternalApiError {
    data class Api(val apiError: ApiError<GithubErrorBody>) : FetchAndPersistDemonError()
    object FailedToPersist : FetchAndPersistDemonError()
}