package com.defaultxyz.githubclient.ui.main

import com.defaultxyz.githubclient.model.DataItem
import com.defaultxyz.githubclient.model.Repository
import com.defaultxyz.githubclient.model.User

class MainPresenterImpl : MainContract.Presenter {
    private var view: MainContract.View? = null
    private val data: MutableList<DataItem> = mutableListOf()

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun onQueryChanged(query: String?) {
        if (query == null) return
        view?.requestUsers(query)
        view?.requestRepositories(query)
    }

    override fun onUsersReceived(users: List<User>) {
        data.addAll(users)
        data.sortBy { it.id }
        view?.updateUi(data)
    }

    override fun onRepositoriesReceived(repositories: List<Repository>) {
        data.addAll(repositories)
        data.sortBy { it.id }
        view?.updateUi(data)
    }
}