package com.defaultxyz.githubclient.mock

import com.defaultxyz.githubclient.injection.core.AppModule
import com.defaultxyz.githubclient.injection.core.CoreComponent
import com.defaultxyz.githubclient.ui.MainApplication

class MockMainApplication : MainApplication() {
    override fun buildCoreComponent(): CoreComponent {
        return DaggerMockCoreComponent.builder()
                .appModule(AppModule(this))
                .networkModule(MockNetworkModule())
                .build()
    }
}