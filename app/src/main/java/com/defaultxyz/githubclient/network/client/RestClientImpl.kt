package com.defaultxyz.githubclient.network.client

import com.defaultxyz.githubclient.model.Repository
import com.defaultxyz.githubclient.model.User
import javax.inject.Inject

class RestClientImpl @Inject constructor(
        private val retrofitClient: RetrofitClient) : RestClient {

    override fun searchUsers(query: String): List<User> {
        val response = retrofitClient.searchUsers(query).execute()
        if (response.isSuccessful) {
            val body = response.body() ?: return emptyList()
            return body.items.map { User(it.id, it.login) }
        }
        return emptyList()
    }

    override fun searchRepositories(query: String): List<Repository> {
        val response = retrofitClient.searchRepositories(query).execute()
        if (response.isSuccessful) {
            val body = response.body() ?: return emptyList()
            return body.items.map { Repository(it.id, it.fullName) }
        }
        return emptyList()
    }
}