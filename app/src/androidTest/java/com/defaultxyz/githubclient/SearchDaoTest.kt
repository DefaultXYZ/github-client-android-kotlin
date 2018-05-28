package com.defaultxyz.githubclient

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.defaultxyz.githubclient.data.database.AppDatabase
import com.defaultxyz.githubclient.data.database.SearchDao
import com.defaultxyz.githubclient.data.model.ItemType
import com.defaultxyz.githubclient.util.TestUtil
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class SearchDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var dao: SearchDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.searchDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testWriteReadSearchEntry() {
        val entry = "Abc"
        val expected = TestUtil.newSearchEntry(entry, ItemType.USER)
        dao.insertNewSearchEntry(expected)
        val actual = dao.getSearchEntries().find { it.entry == entry }
        assertNotNull(actual)
        assertEquals(actual, expected)
    }

    @Test
    fun testClearItem() {
        val expected = "Abc"
        val searchEntry = TestUtil.newSearchEntry(expected, ItemType.USER)
        val entries = TestUtil.getSavedEntries(searchEntry)
        entries.forEach { dao.insertNewSearchEntry(it) }

        dao.deleteSearchEntry(expected)

        val actual = dao.getSearchEntries().find { it.entry == expected }
        assertNull(actual)
    }

    @Test
    fun testClearOldData() {
        val today = Date(TestUtil.TODAY)
        val actualDate = Calendar.getInstance().also {
            it.timeInMillis = TestUtil.TODAY
            it.set(Calendar.DATE, -1)           // Minus one day to make data be actual
        }.time
        val expected = TestUtil.newSearchEntry("abc", ItemType.USER, actualDate)
        val entries = TestUtil.getSavedEntries(expected)
        entries.forEach { dao.insertNewSearchEntry(it) }

        dao.deleteOldSearchEntries(today)

        val list = dao.getSearchEntries()
        assertTrue(list.size == 1)
        val actual = list.first()
        assertEquals(actual, expected)
    }
}