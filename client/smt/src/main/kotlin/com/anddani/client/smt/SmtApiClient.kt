package com.anddani.client.smt

import com.anddani.client.smt.data.RemoteDemon
import com.anddani.common.RemoteErrorBody
import com.anddani.common.ApiError
import com.github.michaelbull.result.Result

interface SmtApiClient {
    suspend fun getDemons(): Result<Map<String, RemoteDemon>, ApiError<RemoteErrorBody>>
}
