package com.anddani.common.errors

sealed class SearchError : InternalApiError {
    object SearchQueryParamMissing : SearchError()
}