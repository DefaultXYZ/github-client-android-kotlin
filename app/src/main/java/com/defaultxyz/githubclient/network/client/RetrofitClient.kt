package com.defaultxyz.githubclient.network.client

import com.defaultxyz.githubclient.model.SearchRepositoryResponse
import com.defaultxyz.githubclient.model.SearchUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitClient {
    @GET("search/users?per_page=100")
    fun searchUsers(@Query("q") query: String): Call<SearchUserResponse>

    @GET("search/repositories?per_page=100")
    fun searchRepositories(@Query("q") query: String): Call<SearchRepositoryResponse>
}