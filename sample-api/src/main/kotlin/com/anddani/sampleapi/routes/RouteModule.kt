package com.anddani.sampleapi.routes

import com.anddani.common.InternalApiError
import com.anddani.sampleapi.ApiRoute
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
interface RouteModule {
    @Binds
    @IntoSet
    fun GetSearchRoute.bindGetSearchRoute(): ApiRoute<*, InternalApiError>

    @Binds
    @IntoSet
    fun GetRootRoute.bindGetRootRoute(): ApiRoute<*, InternalApiError>

    @Binds
    @IntoSet
    fun GetSyncRoute.bindGetSyncRoute(): ApiRoute<*, InternalApiError>
}
