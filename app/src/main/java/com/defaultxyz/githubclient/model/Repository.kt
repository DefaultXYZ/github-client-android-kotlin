package com.defaultxyz.githubclient.model

import paperparcel.PaperParcel

@PaperParcel
data class Repository(override val id: Long,
                      val fullName: String) : DataItem(id) {
    override var title: String = fullName

    companion object {
        @JvmStatic val CREATOR = PaperParcelRepository.CREATOR
    }
}