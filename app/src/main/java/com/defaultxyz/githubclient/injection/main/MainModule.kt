package com.defaultxyz.githubclient.injection.main

import com.defaultxyz.githubclient.injection.annotations.PerActivity
import com.defaultxyz.githubclient.ui.main.MainContract
import com.defaultxyz.githubclient.ui.main.MainPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @PerActivity
    fun providesPresenter(): MainContract.Presenter {
        return MainPresenterImpl()
    }
}