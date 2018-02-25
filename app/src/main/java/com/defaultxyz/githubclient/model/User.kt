package com.defaultxyz.githubclient.model

import paperparcel.PaperParcel

@PaperParcel
data class User(override val id: Long,
                val login: String) : DataItem(id) {
    override var title: String = login

    companion object {
        @JvmStatic val CREATOR = PaperParcelUser.CREATOR
    }
}