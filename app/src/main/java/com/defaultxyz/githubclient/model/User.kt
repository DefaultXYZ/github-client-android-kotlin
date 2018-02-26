package com.defaultxyz.githubclient.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class User(override var id: Long,
                val login: String) : DataItem(id, login) {
    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(id)
        dest?.writeString(login)
        dest?.writeString(title)
        dest?.writeString(avatarUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField val CREATOR = object : Parcelable.Creator<User> {
            override fun createFromParcel(src: Parcel?): User {
                val id = src?.readLong() ?: 0
                val login = src?.readString() ?: ""
                src?.readString()
                val user = User(id, login)
                user.avatarUrl = src?.readString()
                return user
            }

            override fun newArray(size: Int): Array<User> {
                return arrayOf()
            }
        }
    }

}