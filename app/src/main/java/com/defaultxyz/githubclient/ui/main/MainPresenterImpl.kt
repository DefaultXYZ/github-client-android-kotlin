package com.defaultxyz.githubclient.ui.main

import com.defaultxyz.githubclient.model.DataItem

class MainPresenterImpl : MainContract.Presenter {
    private var view: MainContract.View? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun onQueryChanged(query: String?) {
        if (query == null) return
        view?.requestData(query)
    }

    override fun onDataReceived(data: List<DataItem>) {
        val sortedData = data.sortedBy { it.id }
        view?.updateUi(sortedData)
    }
}