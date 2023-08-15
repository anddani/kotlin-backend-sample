package com.anddani.sampleapi.dagger

import com.anddani.client.smt.SmtApiClient
import com.anddani.client.smt.SmtApiClientModule
import com.anddani.client.smt.SmtHttpClientModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SmtApiClientModule::class,
        SmtHttpClientModule::class,
    ]
)
interface ApplicationComponent {
    val smtApiClient: SmtApiClient
}