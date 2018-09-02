package com.googry.coinhelper.di

import com.googry.coinhelper.BuildConfig
import com.googry.coinhelper.network.api.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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

    bean {
        Retrofit.Builder()
                .baseUrl(BuildConfig.CoinoneRestUrl)
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()
                .create(CoinoneApi::class.java)
    }
    bean {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BithumbRestUrl)
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()
                .create(BithumbApi::class.java)
    }
    bean {
        Retrofit.Builder()
                .baseUrl(BuildConfig.UpbitRestUrl)
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()
                .create(UpbitApi::class.java)
    }
    bean {
        Retrofit.Builder()
                .baseUrl(BuildConfig.GopaxRestUrl)
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()
                .create(GopaxApi::class.java)
    }
    bean {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BinanceRestUrl)
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()
                .create(BinanceApi::class.java)
    }
    bean {
        Retrofit.Builder()
                .baseUrl(BuildConfig.BitfinexRestUrl)
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()
                .create(BitfinexApi::class.java)
    }
    bean {
        Retrofit.Builder()
                .baseUrl(BuildConfig.HuobiRestUrl)
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()
                .create(HuobiApi::class.java)
    }
    bean {
        Retrofit.Builder()
                .baseUrl(BuildConfig.CoinexRestUrl)
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()
                .create(CoinexApi::class.java)
    }
    bean {
        Retrofit.Builder()
                .baseUrl(BuildConfig.HitbtcRestUrl)
                .client(get())
                .addCallAdapterFactory(get())
                .addConverterFactory(get())
                .build()
                .create(HitbtcApi::class.java)
    }
}