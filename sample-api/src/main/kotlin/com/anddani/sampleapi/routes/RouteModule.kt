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
    fun SearchRoute.bindSearchRoute(): ApiRoute<*, InternalApiError>

    @Binds
    @IntoSet
    fun RootRoute.bindRootRoute(): ApiRoute<*, InternalApiError>

    @Binds
    @IntoSet
    fun SyncRoute.bindSyncRoute(): ApiRoute<*, InternalApiError>
}
