package com.defaultxyz.githubclient.injection.core

import com.defaultxyz.githubclient.injection.annotations.PerApplication
import dagger.Component

@PerApplication
@Component(modules = arrayOf(AppModule::class))
interface CoreComponent