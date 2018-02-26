package com.defaultxyz.githubclient.main

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.model.DataItem
import com.defaultxyz.githubclient.model.Repository
import com.defaultxyz.githubclient.model.User
import com.defaultxyz.githubclient.network.service.RestFunction
import com.defaultxyz.githubclient.network.service.RestKey
import com.defaultxyz.githubclient.network.service.RestService
import com.defaultxyz.githubclient.ui.details.DetailsActivity
import com.defaultxyz.githubclient.ui.main.MainActivity
import com.defaultxyz.githubclient.ui.main.MainContract
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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

    @Mock private lateinit var presenter: MainContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        controller = Robolectric.buildActivity(MainActivity::class.java)
                .create().start().visible()
        activity = controller.get()
        activity.searchDelay = 0L
        mockResponses()
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
    fun onSearch_shouldSendRequest() {
        searchView.setQuery("a", false)

        val expectedIntent = Intent(activity, RestService::class.java)
        val expectedAction = RestService.ACTION
        val expectedFunction = RestFunction.SEARCH
        val actualIntent = ShadowApplication.getInstance().nextStartedService

        assertEquals(expectedIntent.component, actualIntent.component)
        assertEquals(expectedAction, actualIntent.action)
        assertEquals(expectedFunction, actualIntent.extras.getSerializable(RestKey.FUNCTION))
    }

    @Test
    fun onDataReceived_shouldUpdateUi() {
        onDataLoaded()
        assertEquals(3, resultList.adapter.itemCount)
    }

    @Test
    fun onListUpdate_shouldHaveUsers() {
        onDataLoaded()
        val userViewType = 0
        assertEquals(userViewType, resultList.adapter.getItemViewType(0))
    }

    @Test
    fun onListUpdate_shouldHaveRepositories() {
        onDataLoaded()
        val repoViewType = 1
        assertEquals(repoViewType, resultList.adapter.getItemViewType(1))
    }

    @Test
    fun onUserClick_shouldOpenUserDetails() {
        onDataLoaded()
        resultList.getChildAt(0).performClick()
        val expectedIntent = Intent(activity, DetailsActivity::class.java)
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
        verify(presenter, atLeastOnce()).attachView(activity)
    }

    private fun mockResponses() {
        whenever(presenter.onDataReceived(any())).then {
            val dataList = (it.arguments[0] as List<*>).filterIsInstance<DataItem>()
            activity.updateUi(dataList)
        }
        whenever(presenter.onQueryChanged(any())).then { activity.requestData("a") }
    }

    private fun onDataLoaded() {
        val mockList = arrayListOf(
                User(0, "Aaa"),
                Repository(1, "Comp/Aba"),
                User(2, "Acde")
        )
        val intent = Intent()
        intent.putParcelableArrayListExtra(RestKey.DATA, mockList)
        activity.onReceive(RestFunction.SEARCH, intent)
    }
}