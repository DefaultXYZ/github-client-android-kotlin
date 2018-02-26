package com.defaultxyz.githubclient.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.model.User
import com.defaultxyz.githubclient.network.service.RestFunction
import com.defaultxyz.githubclient.network.service.RestKey
import com.defaultxyz.githubclient.network.service.RestListener
import com.defaultxyz.githubclient.ui.MainApplication
import com.defaultxyz.githubclient.ui.base.BaseActivity
import com.defaultxyz.githubclient.ui.utils.DependencyComponent
import com.squareup.picasso.Picasso

const val USER_KEY = "DetailsActivity.UserKey"

class DetailsActivity : BaseActivity(), DependencyComponent, RestListener {
    @BindView(R.id.avatar) lateinit var avatarView: ImageView
    @BindView(R.id.username) lateinit var usernameView: TextView
    @BindView(R.id.star_label) lateinit var starLabel: TextView
    @BindView(R.id.progress_bar) lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        ButterKnife.bind(this)
        setLoading(true)
        val user = intent.getParcelableExtra<User>(USER_KEY)
        usernameView.text = user.login
        startRestService(RestFunction.STAR_COUNT, user.login)
        user.avatarUrl?.let {
            Picasso.with(this).load(it).into(avatarView)
        }
    }

    override fun onReceive(function: RestFunction, intent: Intent) {
        if (function == RestFunction.STAR_COUNT) {
            val stars = intent.getIntExtra(RestKey.DATA_INT, -1)
            starLabel.text = if (stars == -1) "-" else stars.toString()
            setLoading(false)
        }
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        setLoading(false)
    }

    override fun injectComponent(application: MainApplication) {
        application.coreComponent.inject(this)
    }

    override fun destroyComponent(application: MainApplication) {}

    private fun setLoading(isLoad: Boolean) {
        progressBar.visibility = if (isLoad) View.VISIBLE else View.INVISIBLE
    }
}
