package com.defaultxyz.githubclient.injection.core

import android.app.Application
import android.content.Context
import com.defaultxyz.githubclient.injection.annotations.PerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    @PerApplication
    fun providesApplicationContext(): Context = application.applicationContext
}