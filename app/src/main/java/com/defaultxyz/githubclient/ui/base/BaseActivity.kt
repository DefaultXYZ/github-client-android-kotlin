package com.defaultxyz.githubclient.ui.base

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import com.defaultxyz.githubclient.network.service.*
import com.defaultxyz.githubclient.ui.MainApplication
import com.defaultxyz.githubclient.ui.utils.DependencyComponent

abstract class BaseActivity : AppCompatActivity() {
    private val intentFilter = IntentFilter(RestService.ACTION)
    private val restReceiver = RestBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (this is DependencyComponent)
            this.injectComponent(application as MainApplication)
        if (this is RestListener)
            this.restReceiver.listener = this
    }

    override fun onResume() {
        super.onResume()
        if (this is RestListener)
            LocalBroadcastManager.getInstance(this)
                    .registerReceiver(restReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        if (this is RestListener)
            LocalBroadcastManager.getInstance(this).unregisterReceiver(restReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this is DependencyComponent)
            this.destroyComponent(application as MainApplication)
    }

    protected fun startRestService(function: RestFunction, data: String) {
        val intent = Intent(this, RestService::class.java)
        intent.action = RestService.ACTION
        intent.putExtra(RestKey.FUNCTION, function)
        intent.putExtra(RestKey.DATA_STRING, data)
        startService(intent)
    }
}