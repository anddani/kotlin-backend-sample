package com.sample.client.smt

import com.sample.client.smt.data.RemoteDemon
import com.sample.common.RemoteErrorBody
import com.sample.common.ApiError
import com.github.michaelbull.result.Result

interface SmtApiClient {
    suspend fun getDemons(): Result<Map<String, RemoteDemon>, ApiError<RemoteErrorBody>>
}
