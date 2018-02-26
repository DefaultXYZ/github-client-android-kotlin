package com.defaultxyz.githubclient.mock

import com.defaultxyz.githubclient.injection.annotations.PerApplication
import com.defaultxyz.githubclient.injection.core.AppModule
import com.defaultxyz.githubclient.injection.core.CoreComponent
import com.defaultxyz.githubclient.injection.core.NetworkModule
import com.defaultxyz.githubclient.main.MainUiTest
import dagger.Component

@PerApplication
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface MockCoreComponent : CoreComponent {
    fun inject(test: MainUiTest)
}