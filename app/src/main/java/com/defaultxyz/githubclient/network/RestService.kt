package com.defaultxyz.githubclient.network

import android.app.IntentService
import android.content.Intent

class RestService : IntentService("RestService") {
    override fun onHandleIntent(p0: Intent?) {

    }

    companion object {
        const val ACTION = "com.defaultxyz.githubclient.RestService"
    }
}