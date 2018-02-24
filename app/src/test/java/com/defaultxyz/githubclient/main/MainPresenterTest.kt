package com.defaultxyz.githubclient.main

import com.defaultxyz.githubclient.ui.main.MainContract
import com.defaultxyz.githubclient.ui.main.MainPresenterImpl
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainPresenterTest {
    @Mock lateinit var mockView: MainContract.View

    private lateinit var presenter: MainContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenterImpl()
        presenter.attachView(mockView)
    }

    @After
    fun tearDown() {
        presenter.detachView(mockView)
    }

    @Test
    fun onQueryChanged_shouldRequestUsers() {
        presenter.onQueryChanged("a")
        verify(mockView).requestUsers(eq("a"))
    }

    @Test
    fun onQueryChanged_shouldRequestRepositories() {
        presenter.onQueryChanged("a")
        verify(mockView).requestRepositories(eq("a"))
    }

    @Test
    fun onDataReceived_shouldSortAscending() {
        presenter.onUsersReceived(emptyList())
    }

    @Test
    fun onDataReceived_shouldUpdateUi() {
        verify(mockView).updateUi(any())
    }
}