package com.defaultxyz.githubclient.network

import android.content.Intent

interface RestListener {
    fun onReceive(function: RestFunction, intent: Intent)
}