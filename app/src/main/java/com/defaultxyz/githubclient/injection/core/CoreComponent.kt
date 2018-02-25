package com.defaultxyz.githubclient.injection.core

import com.defaultxyz.githubclient.injection.annotations.PerApplication
import com.defaultxyz.githubclient.network.RestService
import dagger.Component

@PerApplication
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface CoreComponent {
    fun inject(service: RestService)
}