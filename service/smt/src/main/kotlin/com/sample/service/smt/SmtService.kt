package com.sample.service.smt

import com.sample.common.FetchAndPersistDemonError
import com.github.michaelbull.result.Result

interface SmtService {
    suspend fun fetchAndPersistDemons(): Result<Unit, FetchAndPersistDemonError>
}