package com.anddani.service.smt

import dagger.Binds
import dagger.Module

@Module
interface SmtServiceModule {
    @Binds
    fun SmtServiceImpl.bindSmtService(): SmtService
}
