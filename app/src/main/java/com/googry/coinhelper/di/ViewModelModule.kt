package com.googry.coinhelper.di

import com.googry.coinhelper.viewmodel.CoinListViewModel
import com.googry.coinhelper.viewmodel.CoinSortViewModel
import com.googry.coinhelper.viewmodel.ExchangeSelectViewModel
import com.googry.coinhelper.viewmodel.WelcomeViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val viewModelModule = applicationContext {
    viewModel { WelcomeViewModel() }
    viewModel { CoinListViewModel(get()) }
    viewModel { ExchangeSelectViewModel(get()) }
    viewModel { CoinSortViewModel() }
}