package com.anddani.repository.smt

import com.anddani.SmtDb
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmtRepositoryImpl @Inject constructor(
    private val smtDb: SmtDb
) : SmtRepository {

}
