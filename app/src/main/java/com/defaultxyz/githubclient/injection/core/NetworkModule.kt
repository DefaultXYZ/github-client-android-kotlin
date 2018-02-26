package com.defaultxyz.githubclient.injection.core

import android.content.Context
import com.defaultxyz.githubclient.R
import com.defaultxyz.githubclient.injection.annotations.PerApplication
import com.defaultxyz.githubclient.network.client.RestClient
import com.defaultxyz.githubclient.network.client.RestClientImpl
import com.defaultxyz.githubclient.network.client.RetrofitClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class NetworkModule {

    @Provides
    @PerApplication
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor {
                    val newRequest = it.request().newBuilder()
                            .addHeader("Accept", "application/vnd.github.v3+json")
                            .build()
                    it.proceed(newRequest)
                }.build()
    }

    @Provides
    @PerApplication
    fun providesRetrofitClient(context: Context, client: OkHttpClient): RetrofitClient {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.github_url))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(RetrofitClient::class.java)
    }

    @Provides
    @PerApplication
    open fun providesRestClient(retrofitClient: RetrofitClient): RestClient {
        return RestClientImpl(retrofitClient)
    }
}