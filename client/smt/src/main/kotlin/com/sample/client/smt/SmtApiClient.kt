package com.sample.client.smt

import com.github.michaelbull.result.Result
import com.sample.client.smt.data.RemoteDemon
import com.sample.client.smt.data.RemoteErrorBody

interface SmtApiClient {
    suspend fun getDemons(): Result<Map<String, RemoteDemon>, ApiError<RemoteErrorBody>>
}
