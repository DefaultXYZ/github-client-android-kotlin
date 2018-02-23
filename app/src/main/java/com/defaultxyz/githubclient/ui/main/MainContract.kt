package com.defaultxyz.githubclient.ui.main

interface MainContract {
    interface View {
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView(view: View)
    }
}