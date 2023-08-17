package com.anddani.sampleapi.dagger

import com.anddani.client.smt.SmtApiClientModule
import com.anddani.client.smt.SmtHttpClientModule
import com.anddani.repository.smt.SmtDatabaseModule
import com.anddani.repository.smt.SmtRepositoryModule
import com.anddani.sampleapi.ApiRoute
import com.anddani.sampleapi.routes.RouteModule
import com.anddani.service.smt.SmtService
import com.anddani.service.smt.SmtServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SmtApiClientModule::class,
        SmtHttpClientModule::class,
        SmtDatabaseModule::class,
        SmtRepositoryModule::class,
        SmtServiceModule::class,
        RouteModule::class,
    ]
)
interface ApplicationComponent {
    val smtService: SmtService
    val routes: Set<ApiRoute<*, *>>
}