package com.defaultxyz.githubclient.data.database.converter

import android.arch.persistence.room.TypeConverter
import com.defaultxyz.githubclient.data.model.ItemType

class TypeConverter {
    @TypeConverter
    fun toTypeByte(type: ItemType): Byte {
        return type.typeId
    }

    @TypeConverter
    fun toItemType(typeId: Byte): ItemType {
        return ItemType.valueOf(typeId)
    }
}