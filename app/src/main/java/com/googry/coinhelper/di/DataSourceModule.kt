package com.googry.coinhelper.di

import com.googry.coinhelper.data.source.MainExchangeDataSource
import com.googry.coinhelper.data.source.MainExchangeRepository
import com.googry.coinhelper.data.source.ticker.*
import com.googry.coinhelper.network.api.BithumbApi
import org.koin.dsl.module.applicationContext

const val COMMON_TICKER_DATA_SOURCE = "COMMON_TICKER_DATA_SOURCE"
const val COINONE_TICKER_DATA_SOURCE = "COINONE_TICKER_DATA_SOURCE"
const val UPBIT_TICKER_DATA_SOURCE = "UPBIT_TICKER_DATA_SOURCE"
const val BITHUMB_TICKER_DATA_SOURCE = "BITHUMB_TICKER_DATA_SOURCE"

val dataSourceModule = applicationContext {
    bean(COMMON_TICKER_DATA_SOURCE) {
        TickerRepository(get()) as TickerDataSource
    }
    bean(COINONE_TICKER_DATA_SOURCE) {
        CoinoneTickerRepository(get()) as TickerDataSource
    }
    bean(UPBIT_TICKER_DATA_SOURCE) {
        UpbitTickerRepository(get()) as TickerDataSource
    }
    bean(BITHUMB_TICKER_DATA_SOURCE) {
        BithumbTickerRepository(get()) as TickerDataSource
    }
    bean {
        MainExchangeRepository(get(),
                get(COINONE_TICKER_DATA_SOURCE),
                get(UPBIT_TICKER_DATA_SOURCE),
                get(BITHUMB_TICKER_DATA_SOURCE)) as MainExchangeDataSource
    }
}