package com.defaultxyz.githubclient.network.service

import android.content.Intent

interface RestListener {
    fun onReceive(function: RestFunction, intent: Intent)
    fun onError(message: String)
}