package com.googry.coinhelper.di

import com.googry.coinhelper.data.source.MainExchangeDataSource
import com.googry.coinhelper.data.source.MainExchangeRepository
import com.googry.coinhelper.data.source.TickerDataSource
import com.googry.coinhelper.data.source.TickerRepository
import org.koin.dsl.module.applicationContext

val dataSourceModule = applicationContext {
    bean { TickerRepository(get(COINONE_NETWORK)) as TickerDataSource }
    bean { MainExchangeRepository(get()) as MainExchangeDataSource }
}