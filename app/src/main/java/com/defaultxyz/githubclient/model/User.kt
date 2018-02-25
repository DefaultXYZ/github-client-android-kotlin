package com.defaultxyz.githubclient.model

import android.os.Parcel
import android.os.Parcelable

data class User(override var id: Long,
                val login: String) : DataItem(id, login) {

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(id)
        dest?.writeString(login)
        dest?.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<User> {
            override fun createFromParcel(src: Parcel?): User {
                val id = src?.readLong() ?: 0
                val login = src?.readString() ?: ""
                return User(id, login)
            }

            override fun newArray(size: Int): Array<User> {
                return arrayOf()
            }
        }
    }

}