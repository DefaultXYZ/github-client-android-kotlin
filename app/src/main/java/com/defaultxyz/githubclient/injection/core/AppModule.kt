package com.defaultxyz.githubclient.injection.core

import android.app.Application
import android.content.Context
import dagger.Module

@Module
class AppModule(private val application: Application) {

    fun providesApplicationContext(): Context = application.applicationContext
}