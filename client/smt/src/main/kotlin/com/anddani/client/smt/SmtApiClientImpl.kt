package com.anddani.client.smt

import com.github.michaelbull.result.Result
import com.anddani.client.smt.data.RemoteDemon
import com.anddani.common.ApiError
import com.anddani.common.RemoteErrorBody
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmtApiClientImpl @Inject constructor(
    private val ktor: HttpClient
) : SmtApiClient {

    override suspend fun getDemons(): Result<Map<String, RemoteDemon>, ApiError<RemoteErrorBody>> = wrapApiCallToResult {
        ktor.get("aqiu384/megaten-fusion-tool/master/src/app/smt4/data/demon-data.json")
    }
}
