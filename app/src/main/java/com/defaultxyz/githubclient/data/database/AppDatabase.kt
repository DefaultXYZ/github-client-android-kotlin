package com.defaultxyz.githubclient.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.defaultxyz.githubclient.data.database.converter.DateConverter
import com.defaultxyz.githubclient.data.database.converter.TypeConverter
import com.defaultxyz.githubclient.data.model.SearchEntry

private val LOCK = Object()
private const val DB_NAME = "github_db"

@Database(entities = [SearchEntry::class], version = 1)
@TypeConverters(TypeConverter::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(LOCK) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(context.applicationContext,
                                AppDatabase::class.java, DB_NAME).build()
                    }
                }
            }
            return instance!!
        }
    }

    abstract fun searchDao(): SearchDao
}