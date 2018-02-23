package com.defaultxyz.githubclient.main

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.ui.main.MainActivity
import com.defaultxyz.githubclient.ui.main.MainContract
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainViewTest {
    private lateinit var activity: MainActivity
    private lateinit var searchView: SearchView
    private lateinit var resultList: RecyclerView

    @Mock lateinit var presenter: MainContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = Robolectric.setupActivity(MainActivity::class.java)
        activity.presenter = presenter
        searchView = activity.findViewById(R.id.search_view)
        resultList = activity.findViewById(R.id.result_list)
    }

    @Test
    fun onSearch_shouldInvokePresenter() {

    }

    @Test
    fun onSearch_shouldLoadUsers() {

    }

    @Test
    fun onSearch_shouldLoadRepositories() {

    }

    @Test
    fun onDataReceived_shouldUpdateUi() {

    }

    @Test
    fun onListUpdate_shouldHaveUsers() {

    }

    @Test
    fun onListUpdate_shouldHaveRepositories() {

    }

    @Test
    fun onUserClick_shouldOpenUserDetails() {

    }
}