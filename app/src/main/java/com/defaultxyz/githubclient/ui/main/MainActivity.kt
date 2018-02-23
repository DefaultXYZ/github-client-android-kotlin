package com.defaultxyz.githubclient.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.defaultxyz.githubclient.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
