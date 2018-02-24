package com.defaultxyz.githubclient.ui.main

import com.defaultxyz.githubclient.model.Repository
import com.defaultxyz.githubclient.model.User

interface MainContract {
    interface View {
        fun requestUsers(query: String)
        fun requestRepositories(query: String)
        fun updateUi(list: List<Any>)
    }

    interface Presenter {
        fun onQueryChanged(query: String?)
        fun attachView(view: View)
        fun detachView()
        fun onUsersReceived(users: List<User>)
        fun onRepositoriesReceived(repositories: List<Repository>)
    }
}