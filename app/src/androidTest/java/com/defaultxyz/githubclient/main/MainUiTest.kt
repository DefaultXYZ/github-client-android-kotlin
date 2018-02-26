package com.defaultxyz.githubclient.main

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.AutoCompleteTextView
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.mock.MockCoreComponent
import com.defaultxyz.githubclient.mock.MockMainApplication
import com.defaultxyz.githubclient.model.Repository
import com.defaultxyz.githubclient.model.User
import com.defaultxyz.githubclient.network.client.RestClient
import com.defaultxyz.githubclient.ui.details.DetailsActivity
import com.defaultxyz.githubclient.ui.main.MainActivity
import com.defaultxyz.githubclient.ui.main.utils.ResultAdapter
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class MainUiTest {
    @get:Rule private val rule = ActivityTestRule<MainActivity>(MainActivity::class.java,
            false, false)

    @Inject lateinit var restClient: RestClient

    @Before
    fun setUp() {
        getMockCoreComponent().inject(this)
        mockResponses()
        rule.launchActivity(Intent())
    }

    @Test
    fun onSearch_shouldDisplayLoading() {
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
                .perform(typeText("a"))
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun onSearch_shouldLoadData() {
        Intents.init()
        rule.activity.searchDelay = 0L
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
                .perform(typeText("a"))
        onView(withId(R.id.result_list))
                .perform(actionOnItemAtPosition<ResultAdapter.DataItemHolder>(0, click()))
        intended(hasComponent(DetailsActivity::class.java.name))
        Intents.release()
    }

    private fun mockResponses() {
        val mockUsers = listOf(
                User(123, "Abc"),
                User(345, "Adc")
        )
        val mockRepositories = listOf(
                Repository(234, "Comp/Avfd")
        )
        `when`(restClient.searchUsers("a")).thenReturn(mockUsers)
        `when`(restClient.searchRepositories("a")).thenReturn(mockRepositories)
    }

    private fun getMockCoreComponent(): MockCoreComponent {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val mockMainApplication = instrumentation.targetContext.applicationContext as MockMainApplication
        return (mockMainApplication.coreComponent as MockCoreComponent)
    }
}