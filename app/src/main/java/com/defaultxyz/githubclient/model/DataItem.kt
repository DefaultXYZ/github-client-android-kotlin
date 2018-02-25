package com.defaultxyz.githubclient.model

import android.os.Parcelable

abstract class DataItem(open val id: Long,
                        val title: String): Parcelable