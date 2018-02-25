package com.defaultxyz.githubclient.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class RestBroadcastReceiver : BroadcastReceiver() {
    var listener: RestListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null) return
        if (intent.hasExtra(RestKey.ERROR)) {
            handleError(intent)
            return
        }
        if (intent.hasExtra(RestKey.FUNCTION)) {
            handleData(intent)
            return
        }
    }

    private fun handleData(intent: Intent) {
        val function: RestFunction = intent.getSerializableExtra(RestKey.FUNCTION) as RestFunction
        listener?.onReceive(function, intent)
    }

    private fun handleError(intent: Intent) {

    }

}