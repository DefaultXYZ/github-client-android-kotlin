package com.defaultxyz.githubclient.model

data class Repository(override val id: Long,
                      val fullName: String) : DataItem(id)