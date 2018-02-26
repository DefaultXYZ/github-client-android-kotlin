package com.defaultxyz.githubclient.model

import com.google.gson.annotations.SerializedName

data class UserResponse(@SerializedName("id")
                        val id: Long,
                        @SerializedName("login")
                        val login: String)

data class RepositoryResponse(@SerializedName("id")
                              val id: Long,
                              @SerializedName("full_name")
                              val fullName: String)

data class SearchUserResponse(val items: List<UserResponse>)

data class SearchRepositoryResponse(val items: List<RepositoryResponse>)
