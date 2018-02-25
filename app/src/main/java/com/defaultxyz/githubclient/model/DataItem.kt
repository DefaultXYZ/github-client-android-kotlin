package com.defaultxyz.githubclient.model

import paperparcel.PaperParcelable

abstract class DataItem(open val id: Long) : PaperParcelable {
    abstract var title: String
}