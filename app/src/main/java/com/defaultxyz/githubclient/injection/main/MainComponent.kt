package com.defaultxyz.githubclient.injection.main

import com.defaultxyz.githubclient.injection.annotations.PerActivity
import com.defaultxyz.githubclient.injection.core.CoreComponent
import com.defaultxyz.githubclient.ui.main.MainActivity
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(CoreComponent::class),
        modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}