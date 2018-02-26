package com.defaultxyz.githubclient.mock

import com.defaultxyz.githubclient.injection.core.NetworkModule
import com.defaultxyz.githubclient.network.client.RestClient
import com.defaultxyz.githubclient.network.client.RetrofitClient
import org.mockito.Mockito.mock

class MockNetworkModule : NetworkModule() {

    override fun providesRestClient(retrofitClient: RetrofitClient): RestClient {
        return mock(RestClient::class.java)
    }
}