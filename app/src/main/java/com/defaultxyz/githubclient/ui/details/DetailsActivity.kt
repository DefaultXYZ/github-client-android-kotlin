package com.defaultxyz.githubclient.ui.details

import android.content.Intent
import android.os.Bundle
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.network.service.RestFunction
import com.defaultxyz.githubclient.network.service.RestListener
import com.defaultxyz.githubclient.ui.MainApplication
import com.defaultxyz.githubclient.ui.base.BaseActivity
import com.defaultxyz.githubclient.ui.utils.DependencyComponent

class DetailsActivity : BaseActivity(), DependencyComponent, RestListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    override fun onReceive(function: RestFunction, intent: Intent) {
    }

    override fun onError(message: String) {
    }

    override fun injectComponent(application: MainApplication) {
    }

    override fun destroyComponent(application: MainApplication) {
    }
}
