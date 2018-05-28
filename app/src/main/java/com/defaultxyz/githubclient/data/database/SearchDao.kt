package com.defaultxyz.githubclient.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.defaultxyz.githubclient.data.model.SearchEntry
import com.defaultxyz.githubclient.util.DateUtil
import java.util.*

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewSearchEntry(entry: SearchEntry)

    @Query("SELECT * FROM search")
    fun getSearchEntries(): List<SearchEntry>

    @Query("DELETE FROM search WHERE entry = :entry")
    fun deleteSearchEntry(entry: String)

    @Query("DELETE FROM search WHERE :today - date > ${DateUtil.SEVEN_DAYS_IN_MS}")
    fun deleteOldSearchEntries(today: Date)

}