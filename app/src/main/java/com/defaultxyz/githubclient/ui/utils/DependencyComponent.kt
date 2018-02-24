package com.defaultxyz.githubclient.ui.utils

import com.defaultxyz.githubclient.ui.MainApplication

interface DependencyComponent {
    fun injectComponent(application: MainApplication)
    fun destroyComponent(application: MainApplication)
}