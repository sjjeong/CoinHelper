package com.googry.coinhelper.di

import com.googry.coinhelper.BuildConfig
import com.googry.coinhelper.network.api.CoinoneApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val COINONE_NETWORK = "COINONE_NETWORK"

val networkModule = applicationContext {
    bean {
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                })
                .build()
    }

    bean {
        GsonConverterFactory.create() as Converter.Factory
    }

    bean {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }

    bean(COINONE_NETWORK) {
        Retrofit.Builder()
                .baseUrl(BuildConfig.CoinoneRestUrl)
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()
                .create(CoinoneApi::class.java) as CoinoneApi
    }
}