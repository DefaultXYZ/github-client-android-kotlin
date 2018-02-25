package com.defaultxyz.githubclient.ui

import android.app.Application
import com.defaultxyz.githubclient.injection.core.AppModule
import com.defaultxyz.githubclient.injection.core.CoreComponent
import com.defaultxyz.githubclient.injection.core.DaggerCoreComponent
import com.defaultxyz.githubclient.injection.core.NetworkModule
import com.defaultxyz.githubclient.injection.main.DaggerMainComponent
import com.defaultxyz.githubclient.injection.main.MainComponent
import com.defaultxyz.githubclient.injection.main.MainModule

class MainApplication : Application() {
    lateinit var coreComponent: CoreComponent
    private var mainComponent: MainComponent? = null

    override fun onCreate() {
        super.onCreate()
        coreComponent = DaggerCoreComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }

    fun buildMainComponent(): MainComponent {
        if (mainComponent == null) {
            mainComponent = DaggerMainComponent.builder()
                    .coreComponent(coreComponent)
                    .mainModule(MainModule())
                    .build()
        }
        return mainComponent!!
    }

    fun destroyMainComponent() {
        this.mainComponent = null
    }
}