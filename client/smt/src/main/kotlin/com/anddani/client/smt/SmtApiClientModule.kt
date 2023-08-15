package com.anddani.client.smt

import dagger.Binds
import dagger.Module

@Module
interface SmtApiClientModule {
    @Binds
    fun SmtApiClientImpl.bindSmtApiClient(): SmtApiClient
}