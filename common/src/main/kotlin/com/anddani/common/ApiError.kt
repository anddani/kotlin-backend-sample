package com.anddani.common

sealed interface ApiError <T> {
    object UnexpectedError : ApiError<Nothing>
    data class FailedToParse(val statusCode: Int) : ApiError<Nothing>
    data class ServerError(
        val statusCode: Int,
        val statusMessage: String,
    ) : ApiError<Nothing>
    data class ClientError<T>(
        val statusCode: Int,
        val statusMessage: String,
        val body: T?,
    ) : ApiError<T>
}