package com.defaultxyz.githubclient.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.defaultxyz.githubclient.ui.MainApplication
import com.defaultxyz.githubclient.ui.utils.DependencyComponent

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (this is DependencyComponent)
            this.injectComponent(application as MainApplication)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this is DependencyComponent)
            this.destroyComponent(application as MainApplication)
    }
}