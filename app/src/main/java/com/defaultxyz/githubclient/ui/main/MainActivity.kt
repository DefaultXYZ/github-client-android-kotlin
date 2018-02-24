package com.defaultxyz.githubclient.ui.main

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import butterknife.BindView
import butterknife.ButterKnife
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.ui.MainApplication
import com.defaultxyz.githubclient.ui.base.BaseActivity
import com.defaultxyz.githubclient.ui.utils.DependencyComponent
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, DependencyComponent, SearchView.OnQueryTextListener {
    @BindView(R.id.search_view) lateinit var searchView: SearchView
    @BindView(R.id.result_list) lateinit var resultList: RecyclerView

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        searchView.setOnQueryTextListener(this)
    }

    override fun injectComponent(application: MainApplication) {
        application.buildMainComponent().inject(this)
    }

    override fun destroyComponent(application: MainApplication) {
        application.destroyMainComponent()
    }

    override fun requestUsers(query: String) {
    }

    override fun requestRepositories(query: String) {
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun updateUi(list: List<Any>) {
    }
}
