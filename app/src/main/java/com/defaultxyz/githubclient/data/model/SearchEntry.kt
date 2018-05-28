package com.defaultxyz.githubclient.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "search", indices = [Index(value = ["entry"], unique = true)])
data class SearchEntry @Ignore constructor(val entry: String,
                                           val type: ItemType,
                                           val date: Date) {
    @field:PrimaryKey(autoGenerate = true) var id: Long? = null

    constructor(id: Long, entry: String,
                type: ItemType, date: Date) : this(entry, type, date) {
        this.id = id
    }
}