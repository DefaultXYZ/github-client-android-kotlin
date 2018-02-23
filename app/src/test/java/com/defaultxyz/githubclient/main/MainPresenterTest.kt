package com.defaultxyz.githubclient.main

import com.defaultxyz.githubclient.ui.main.MainContract
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainPresenterTest {
    @Mock lateinit var mockView: MainContract.View

    private lateinit var presenter: MainContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        // TODO: Initialize presenter
        presenter.attachView(mockView)
    }

    @After
    fun tearDown() {
        presenter.detachView(mockView)
    }

    @Test
    fun onQueryChanged_shouldRequestUsers() {

    }

    @Test
    fun onQueryChanged_shouldRequestRepositories() {

    }

    @Test
    fun onDataReceived_shouldSortAscending() {

    }

    @Test
    fun onDataReceived_shouldUpdateUi() {

    }
}