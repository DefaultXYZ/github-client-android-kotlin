package com.defaultxyz.githubclient.network.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v4.content.LocalBroadcastManager
import com.defaultxyz.githubclient.model.DataItem
import com.defaultxyz.githubclient.network.client.RestClient
import com.defaultxyz.githubclient.ui.MainApplication
import javax.inject.Inject

class RestService : IntentService("RestService") {
    @Inject lateinit var context: Context
    @Inject lateinit var restClient: RestClient

    private lateinit var resultIntent: Intent
    private lateinit var dataString: String
    private lateinit var currentFunction: RestFunction

    override fun onHandleIntent(intent: Intent?) {
        (application as MainApplication).coreComponent.inject(this)
        resultIntent = Intent(ACTION)
        try {
            checkInternetConnection()
            initData(intent)
            sendRequest()
        } catch (e: Exception) {
            resultIntent.putExtra(RestKey.ERROR, e.message)
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(resultIntent)
    }

    private fun initData(intent: Intent?) {
        if (intent == null) throw Exception("Client error")
        currentFunction = intent.getSerializableExtra(RestKey.FUNCTION) as RestFunction
        if (intent.hasExtra(RestKey.DATA_STRING))
            dataString = intent.getStringExtra(RestKey.DATA_STRING)
        resultIntent.putExtra(RestKey.FUNCTION, currentFunction)
    }

    private fun sendRequest() {
        when (currentFunction) {
            RestFunction.SEARCH -> doSearch()
        }
    }

    private fun doSearch() {
        val users = restClient.searchUsers(dataString)
        val repositories = restClient.searchRepositories(dataString)
        val resultList: MutableList<DataItem> = mutableListOf()
        resultList.addAll(users)
        resultList.addAll(repositories)
        resultIntent.putParcelableArrayListExtra(RestKey.DATA_STRING, ArrayList(resultList))
    }

    private fun checkInternetConnection() {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (manager.activeNetworkInfo == null || !manager.activeNetworkInfo.isConnected)
            throw Exception("Connection error")
    }

    companion object {
        const val ACTION = "com.defaultxyz.githubclient.RestService"
    }
}

object RestKey {
    const val FUNCTION = "RestFunction"
    const val ERROR = "RestError"
    const val DATA_STRING = "RestDataString"
    const val DATA_INT = "RestDataInt"
    const val DATA = "RestData"
}

enum class RestFunction {
    SEARCH, STAR_COUNT
}