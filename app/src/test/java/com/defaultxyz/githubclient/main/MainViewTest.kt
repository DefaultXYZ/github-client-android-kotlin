package com.defaultxyz.githubclient.main

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.network.RestFunctions
import com.defaultxyz.githubclient.network.RestKeys
import com.defaultxyz.githubclient.network.RestService
import com.defaultxyz.githubclient.ui.main.MainActivity
import com.defaultxyz.githubclient.ui.main.MainContract
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class MainViewTest {
    private lateinit var activity: MainActivity
    private lateinit var controller: ActivityController<MainActivity>
    private lateinit var searchView: SearchView
    private lateinit var resultList: RecyclerView

    @Mock lateinit var presenter: MainContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        controller = Robolectric.buildActivity(MainActivity::class.java)
                .create().start()
        activity = controller.get()
        activity.presenter = presenter
        searchView = activity.findViewById(R.id.search_view)
        resultList = activity.findViewById(R.id.result_list)
    }

    @Test
    fun onSearch_shouldInvokePresenter() {
        searchView.setQuery("a", false)
        verify(presenter).onQueryChanged(eq("a"))
    }

    @Test
    fun onSearch_shouldLoadUsers() {
        searchView.setQuery("a", false)

        val expectedIntent = Intent(activity, RestService::class.java)
        expectedIntent.action = RestService.ACTION
        expectedIntent.putExtra(RestKeys.FUNCTION, RestFunctions.GET_USERS)
        val actualIntent = ShadowApplication.getInstance().nextStartedService

        assertEquals(expectedIntent.component, actualIntent.component)
        assertEquals(expectedIntent.action, actualIntent.action)
        assertEquals(RestFunctions.GET_USERS, actualIntent.extras.getString(RestKeys.FUNCTION))
    }

    @Test
    fun onSearch_shouldLoadRepositories() {
        searchView.setQuery("a", false)

        val expectedIntent = Intent(activity, RestService::class.java)
        expectedIntent.action = RestService.ACTION
        expectedIntent.putExtra(RestKeys.FUNCTION, RestFunctions.GET_REPOSITORIES)
        val actualIntent = ShadowApplication.getInstance().nextStartedService

        assertEquals(expectedIntent.component, actualIntent.component)
        assertEquals(expectedIntent.action, actualIntent.action)
        assertEquals(RestFunctions.GET_REPOSITORIES, actualIntent.extras.getString(RestKeys.FUNCTION))
    }

    @Test
    fun onDataReceived_shouldUpdateUi() {
        searchView.setQuery("a", false)
        assertEquals(3, resultList.adapter.itemCount)
    }

    @Test
    fun onListUpdate_shouldHaveUsers() {
        searchView.setQuery("a", false)
        val userViewType = 0
        assertEquals(userViewType, resultList.adapter.getItemViewType(0))
    }

    @Test
    fun onListUpdate_shouldHaveRepositories() {
        searchView.setQuery("a", false)
        val repoViewType = 1
        assertEquals(repoViewType, resultList.adapter.getItemViewType(0))
    }

    @Test
    fun onUserClick_shouldOpenUserDetails() {
        resultList.getChildAt(0).performClick()
        val expectedIntent = Intent()
        val actualIntent = ShadowApplication.getInstance().nextStartedActivity
        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun onPause_shouldDetachPresenter() {
        controller.pause()
        verify(presenter).detachView()
    }

    @Test
    fun onResume_shouldAttachPresenter() {
        controller.pause().resume()
        verify(presenter, any()).attachView(activity)
    }
}