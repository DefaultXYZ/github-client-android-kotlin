package com.defaultxyz.githubclient.main

import com.defaultxyz.githubclient.model.Repository
import com.defaultxyz.githubclient.model.User
import com.defaultxyz.githubclient.ui.main.MainContract
import com.defaultxyz.githubclient.ui.main.MainPresenterImpl
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainPresenterTest {
    @Mock private lateinit var mockView: MainContract.View

    private lateinit var presenter: MainContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenterImpl()
        presenter.attachView(mockView)
        mockResponses()
    }

    @After
    fun tearDown() {
        presenter.detachView()
    }

    @Test
    fun onQueryChanged_shouldRequestUsers() {
        presenter.onQueryChanged("a")
        verify(mockView).requestData("a")
    }

    @Test
    fun onDataReceived_shouldSortAscending() {
        val data = listOf(
                User(124, "AAA"),
                User(542, "ABB"),
                Repository(765, "Comp/AC"),
                Repository(324, "Comp/AD")
        )

        presenter.onDataReceived(data)

        val expectedList = listOf(
                User(124, "AAA"),
                Repository(324, "Comp/AD"),
                User(542, "ABB"),
                Repository(765, "Comp/AC")
        )
        verify(mockView, atLeastOnce()).updateUi(eq(expectedList))
    }

    @Test
    fun onDataReceived_shouldUpdateUi() {
        presenter.onQueryChanged("a")
        verify(mockView, atLeastOnce()).updateUi(any())
    }

    private fun mockResponses() {
        whenever(mockView.requestData("a")).then { presenter.onDataReceived(emptyList()) }
    }
}