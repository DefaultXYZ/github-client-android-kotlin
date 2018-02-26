package com.defaultxyz.githubclient.ui.main

import com.defaultxyz.githubclient.model.DataItem

interface MainContract {
    interface View {
        fun requestData(query: String)
        fun updateUi(list: List<DataItem>)
    }

    interface Presenter {
        fun onQueryChanged(query: String?)
        fun attachView(view: View)
        fun detachView()
        fun onDataReceived(data: List<DataItem>)
    }
}