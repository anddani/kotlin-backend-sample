package com.sample.sampleapi.dagger

import com.sample.client.smt.SmtApiClient
import com.sample.client.smt.SmtApiClientModule
import com.sample.client.smt.SmtHttpClientModule
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