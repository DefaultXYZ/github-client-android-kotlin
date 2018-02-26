package com.defaultxyz.githubclient.model

import android.os.Parcel
import android.os.Parcelable

data class Repository(override var id: Long,
                      val fullName: String) : DataItem(id, fullName) {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(id)
        dest?.writeString(fullName)
        dest?.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<Repository> {
            override fun createFromParcel(src: Parcel?): Repository {
                val id = src?.readLong() ?: 0
                val fullName = src?.readString() ?: ""
                return Repository(id, fullName)
            }

            override fun newArray(size: Int): Array<Repository> {
                return arrayOf()
            }
        }
    }
}