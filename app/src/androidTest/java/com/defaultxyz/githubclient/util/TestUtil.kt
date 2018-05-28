package com.defaultxyz.githubclient.util

import com.defaultxyz.githubclient.data.model.ItemType
import com.defaultxyz.githubclient.data.model.SearchEntry
import java.util.*

object TestUtil {
    const val OLD_DATE = 1514761200000 // 01/01/2018 00:00:00 in milliseconds
    const val TODAY = 1533074400000 // 08/01/2018 00:00:00 in milliseconds

    fun newSearchEntry(entry: String, type: ItemType, date: Date? = null): SearchEntry {
        return SearchEntry(entry, type, date ?: Date(OLD_DATE))
    }

    fun getSavedEntries(testableEntry: SearchEntry? = null): List<SearchEntry> {
        val entries = mutableListOf<SearchEntry>()
        entries.add(newSearchEntry("MockUser", ItemType.USER))
        entries.add(newSearchEntry("MockRepo", ItemType.REPO))
        testableEntry?.let { entries.add(it) }
        return entries
    }
}