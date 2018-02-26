package com.defaultxyz.githubclient.details

import android.content.Intent
import android.widget.TextView
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.model.User
import com.defaultxyz.githubclient.network.service.RestFunction
import com.defaultxyz.githubclient.network.service.RestKey
import com.defaultxyz.githubclient.network.service.RestService
import com.defaultxyz.githubclient.ui.details.DetailsActivity
import com.defaultxyz.githubclient.ui.details.USER_KEY
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
    private lateinit var starLabel: TextView

    @Before
    fun setUp() {
        val mockUser = User(123, "abc")
        mockUser.avatarUrl = "mockAvatarUrl"
        val intent = Intent()
        intent.putExtra(USER_KEY, mockUser)
        controller = Robolectric.buildActivity(DetailsActivity::class.java, intent)
                .create().start().visible()
        activity = controller.get()
        usernameText = activity.findViewById(R.id.username)
        starLabel = activity.findViewById(R.id.star_label)
    }

    @Test
    fun onLoadScreen_requestDetails() {
        val expectedIntent = Intent(activity, RestService::class.java)
        val expectedAction = RestService.ACTION
        val expectedFunction = RestFunction.STAR_COUNT
        val actualIntent = ShadowApplication.getInstance().nextStartedService

        assertEquals(expectedIntent.component, actualIntent.component)
        assertEquals(expectedAction, actualIntent.action)
        assertEquals(expectedFunction, actualIntent.extras.getSerializable(RestKey.FUNCTION))
    }

    @Test
    fun onLoadScreen_displayData() {
        assertEquals("abc", usernameText.text.toString())
    }

    @Test
    fun onDataReceived_updateStars() {
        val intent = Intent()
        intent.putExtra(RestKey.DATA_INT, 12)

        activity.onReceive(RestFunction.STAR_COUNT, intent)

        val starText = starLabel.text.toString()
        val actual = starText.toIntOrNull()
        assertEquals(12, actual)
    }
}