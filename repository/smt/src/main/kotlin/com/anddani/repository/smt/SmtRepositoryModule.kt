package com.anddani.repository.smt

import dagger.Binds
import dagger.Module

@Module
interface SmtRepositoryModule {
    @Binds
    fun SmtRepositoryImpl.bindSmtRepository(): SmtRepository
}