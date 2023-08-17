package com.anddani.service.smt

import com.anddani.common.FetchAndPersistDemonError
import com.anddani.service.smt.data.Demon
import com.github.michaelbull.result.Result

interface SmtService {
    suspend fun fetchAndPersistDemons(): Result<Unit, FetchAndPersistDemonError>
    fun searchDemons(query: String): List<Demon>
}