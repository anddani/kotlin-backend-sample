package com.sample.client.smt

import com.sample.client.smt.data.RemoteDemon
import com.github.michaelbull.result.Result
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmtApiClientImpl @Inject constructor(
    private val ktor: HttpClient
) : SmtApiClient {

    override suspend fun getDemons(): Result<Map<String, RemoteDemon>, ApiError<RemoteDemon>> = wrapApiCallToResult {
        ktor.get("aqiu384/megaten-fusion-tool/master/src/app/smt4/data/demon-data.json")
    }
}