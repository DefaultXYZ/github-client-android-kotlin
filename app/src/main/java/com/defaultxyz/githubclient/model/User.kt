package com.defaultxyz.githubclient.model

data class User(override val id: Long,
                val login: String) : DataItem(id)