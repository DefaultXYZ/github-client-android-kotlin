package com.defaultxyz.githubclient.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class RestBroadcastReceiver : BroadcastReceiver() {
    var listener: RestListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null) return
        val function: RestFunction = intent.getSerializableExtra(RestKey.FUNCTION) as RestFunction
        listener?.onReceive(function, intent)
    }

}