package com.defaultxyz.githubclient.utils

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.defaultxyz.githubclient.mock.MockMainApplication

class AndroidTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, MockMainApplication::class.java.name, context)
    }

}