package com.googry.coinhelper.di

import com.googry.coinhelper.viewmodel.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val viewModelModule = applicationContext {
    viewModel { WelcomeViewModel() }
    viewModel { CoinListViewModel(get()) }
    viewModel { ExchangeSelectViewModel(androidApplication(), get()) }
    viewModel { CoinSortViewModel() }
    viewModel { CoinCompareViewModel(get()) }
}