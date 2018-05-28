package com.defaultxyz.githubclient.data.model

enum class ItemType(var typeId: Byte) {
    USER(0), REPO(1);

    companion object {
        @Throws(IllegalArgumentException::class)
        fun valueOf(typeId: Byte): ItemType {
            return values().find { it.typeId == typeId }
                    ?: throw IllegalArgumentException("No github item found")
        }
    }
}