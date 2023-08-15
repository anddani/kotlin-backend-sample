package com.sample.service.smt

import com.sample.client.smt.SmtApiClient
import com.sample.client.smt.data.RemoteDemon
import com.sample.common.FetchAndPersistDemonError
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.binding
import com.github.michaelbull.result.mapError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmtServiceImpl @Inject constructor(
    private val apiClient: SmtApiClient,
) : SmtService {

    override suspend fun fetchAndPersistDemons(): Result<Unit, FetchAndPersistDemonError> = binding {
        val demons: Map<String, RemoteDemon> = apiClient.getDemons()
            .mapError(FetchAndPersistDemonError::Api)
            .bind()

        // TODO: Persist demons
    }
}
