package com.googry.coinhelper.di

import com.googry.coinhelper.data.source.TickerDataSource
import com.googry.coinhelper.data.source.TickerRepository
import org.koin.dsl.module.applicationContext

val dataSourceModule = applicationContext {
    bean { TickerRepository(get(COINONE_NETWORK)) as TickerDataSource}
}