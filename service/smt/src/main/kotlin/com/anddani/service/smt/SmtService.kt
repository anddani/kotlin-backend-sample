package com.anddani.service.smt

import com.anddani.common.FetchAndPersistDemonError
import com.github.michaelbull.result.Result

interface SmtService {
    suspend fun fetchAndPersistDemons(): Result<Unit, FetchAndPersistDemonError>
}