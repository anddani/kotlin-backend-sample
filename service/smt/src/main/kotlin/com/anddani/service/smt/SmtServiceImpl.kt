package com.anddani.service.smt

import com.anddani.client.smt.SmtApiClient
import com.anddani.client.smt.data.RemoteDemon
import com.anddani.common.FetchAndPersistDemonError
import com.anddani.repository.smt.SmtRepository
import com.anddani.service.smt.data.Demon
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.binding
import com.github.michaelbull.result.mapError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmtServiceImpl @Inject constructor(
    private val apiClient: SmtApiClient,
    private val repository: SmtRepository,
) : SmtService {

    override suspend fun fetchAndPersistDemons(): Result<Unit, FetchAndPersistDemonError> = binding {
        val demons: Map<String, RemoteDemon> = apiClient.getDemons()
            .mapError(FetchAndPersistDemonError::Api)
            .bind()

        repository.insertDemonsWithSkills(demons.toDemonWithSkills())
    }

    override fun searchDemons(query: String): List<Demon> =
        repository.selectDemonsWithName(query).toDemons()
}
