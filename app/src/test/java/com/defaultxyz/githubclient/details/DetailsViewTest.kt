package com.defaultxyz.githubclient.details

import android.content.Intent
import android.widget.TextView
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.model.User
import com.defaultxyz.githubclient.network.service.RestFunction
import com.defaultxyz.githubclient.network.service.RestKey
import com.defaultxyz.githubclient.network.service.RestService
import com.defaultxyz.githubclient.ui.details.DetailsActivity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class DetailsViewTest {
    private lateinit var controller: ActivityController<DetailsActivity>
    private lateinit var activity: DetailsActivity

    private lateinit var usernameText: TextView

    @Before
    fun setUp() {
        controller = Robolectric.buildActivity(DetailsActivity::class.java)
                .create().start().visible()
        activity = controller.get()
        usernameText = activity.findViewById(R.id.username)
    }

    @Test
    fun onLoadScreen_requestDetails() {
        val expectedIntent = Intent(activity, RestService::class.java)
        val expectedAction = RestService.ACTION
        val expectedFunction = RestFunction.USER_DETAILS
        val actualIntent = ShadowApplication.getInstance().nextStartedService

        assertEquals(expectedIntent.component, actualIntent.component)
        assertEquals(expectedAction, actualIntent.action)
        assertEquals(expectedFunction, actualIntent.extras.getSerializable(RestKey.FUNCTION))
    }

    @Test
    fun onDataReceive_displayData() {
        val mockUser = User(123, "abc")
        val intent = Intent()
        intent.putExtra(RestKey.DATA, mockUser)

        activity.onReceive(RestFunction.USER_DETAILS, intent)

        assertEquals("abc", usernameText.text.toString())
    }
}