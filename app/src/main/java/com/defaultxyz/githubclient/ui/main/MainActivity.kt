package com.defaultxyz.githubclient.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import butterknife.BindView
import butterknife.ButterKnife
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.model.DataItem
import com.defaultxyz.githubclient.network.RestFunction
import com.defaultxyz.githubclient.network.RestKey
import com.defaultxyz.githubclient.network.RestListener
import com.defaultxyz.githubclient.ui.MainApplication
import com.defaultxyz.githubclient.ui.base.BaseActivity
import com.defaultxyz.githubclient.ui.main.utils.ResultAdapter
import com.defaultxyz.githubclient.ui.utils.DependencyComponent
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, DependencyComponent, RestListener,
        SearchView.OnQueryTextListener {
    @BindView(R.id.search_view) lateinit var searchView: SearchView
    @BindView(R.id.result_list) lateinit var resultList: RecyclerView

    @Inject lateinit var presenter: MainContract.Presenter

    private lateinit var adapter: ResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        presenter.attachView(this)
        adapter = ResultAdapter(this)
        resultList.layoutManager = LinearLayoutManager(this)
        resultList.adapter = adapter
        searchView.setOnQueryTextListener(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    override fun onReceive(function: RestFunction, intent: Intent) {
        if (function == RestFunction.SEARCH) {
            val data = intent.getParcelableArrayListExtra<DataItem>(RestKey.DATA)
            presenter.onDataReceived(data)
        }
    }

    override fun injectComponent(application: MainApplication) {
        application.buildMainComponent().inject(this)
    }

    override fun destroyComponent(application: MainApplication) {
        application.destroyMainComponent()
    }

    override fun requestData(query: String) {
        startRestService(RestFunction.SEARCH, query)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        presenter.onQueryChanged(newText)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun updateUi(list: List<DataItem>) {
        adapter.setData(list)
        adapter.notifyDataSetChanged()
    }
}
