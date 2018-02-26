package com.defaultxyz.githubclient.network.client

import com.defaultxyz.githubclient.model.Repository
import com.defaultxyz.githubclient.model.User

interface RestClient {
    fun searchUsers(query: String): List<User>
    fun searchRepositories(query: String): List<Repository>
}