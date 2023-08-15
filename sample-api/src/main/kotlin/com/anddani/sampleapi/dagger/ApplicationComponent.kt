package com.anddani.sampleapi.dagger

import com.anddani.client.smt.SmtApiClient
import com.anddani.client.smt.SmtApiClientModule
import com.anddani.client.smt.SmtHttpClientModule
import com.anddani.repository.smt.SmtDatabaseModule
import com.anddani.repository.smt.SmtRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SmtApiClientModule::class,
        SmtHttpClientModule::class,
        SmtDatabaseModule::class,
        SmtRepositoryModule::class,
    ]
)
interface ApplicationComponent {
    val smtApiClient: SmtApiClient
}