package com.googry.coinhelper

import android.app.Application
import com.googry.coinhelper.di.dataSourceModule
import com.googry.coinhelper.di.networkModule
import com.googry.coinhelper.di.viewModelModule
import org.koin.android.ext.android.startKoin

class CoinHelperApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(listOf(
                networkModule,
                dataSourceModule,
                viewModelModule
        ))
    }

}