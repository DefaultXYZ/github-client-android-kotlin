package com.defaultxyz.githubclient.ui.main

interface MainContract {
    interface View {
        fun requestUsers(query: String)
        fun requestRepositories(query: String)
        fun updateUi(list: List<Any>)
    }

    interface Presenter {
        fun onQueryChanged(query: String?)
        fun attachView(view: View)
        fun detachView(view: View)
        fun onUsersReceived(users: List<Any>)
        fun onRepositoriesReceived(repositories: List<Any>)
    }
}