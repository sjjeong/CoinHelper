package com.googry.coinhelper.di

import com.googry.coinhelper.data.source.MainExchangeDataSource
import com.googry.coinhelper.data.source.MainExchangeRepository
import com.googry.coinhelper.data.source.ticker.*
import org.koin.dsl.module.applicationContext

const val COINONE_TICKER_DATA_SOURCE = "COINONE_TICKER_DATA_SOURCE"
const val UPBIT_TICKER_DATA_SOURCE = "UPBIT_TICKER_DATA_SOURCE"
const val BITHUMB_TICKER_DATA_SOURCE = "BITHUMB_TICKER_DATA_SOURCE"
const val GOPAX_TICKER_DATA_SOURCE = "GOPAX_TICKER_DATA_SOURCE"
const val BINANCE_TICKER_DATA_SOURCE = "BINANCE_TICKER_DATA_SOURCE"
const val BITFINEX_TICKER_DATA_SOURCE = "BITFINEX_TICKER_DATA_SOURCE"
const val HUOBI_TICKER_DATA_SOURCE = "HUOBI_TICKER_DATA_SOURCE"
const val COINEX_TICKER_DATA_SOURCE = "COINEX_TICKER_DATA_SOURCE"
const val HITBTC_TICKER_DATA_SOURCE = "HITBTC_TICKER_DATA_SOURCE"

val dataSourceModule = applicationContext {
    bean(COINONE_TICKER_DATA_SOURCE) {
        CoinoneTickerRepository(get()) as TickerDataSource
    }
    bean(UPBIT_TICKER_DATA_SOURCE) {
        UpbitTickerRepository(get()) as TickerDataSource
    }
    bean(BITHUMB_TICKER_DATA_SOURCE) {
        BithumbTickerRepository(get()) as TickerDataSource
    }
    bean(GOPAX_TICKER_DATA_SOURCE) {
        GopaxTickerRepository(get()) as TickerDataSource
    }
    bean(BINANCE_TICKER_DATA_SOURCE) {
        BinanceTickerRepository(get()) as TickerDataSource
    }
    bean(BITFINEX_TICKER_DATA_SOURCE) {
        BitfinexTickerRepository(get()) as TickerDataSource
    }
    bean(HUOBI_TICKER_DATA_SOURCE) {
        HuobiTickerRepository(get()) as TickerDataSource
    }
    bean(COINEX_TICKER_DATA_SOURCE) {
        CoinexTickerRepository(get()) as TickerDataSource
    }
    bean(HITBTC_TICKER_DATA_SOURCE) {
        HitbtcTickerRepository(get()) as TickerDataSource
    }
    bean {
        MainExchangeRepository(get(),
                get(COINONE_TICKER_DATA_SOURCE),
                get(UPBIT_TICKER_DATA_SOURCE),
                get(BITHUMB_TICKER_DATA_SOURCE),
                get(GOPAX_TICKER_DATA_SOURCE),
                get(BINANCE_TICKER_DATA_SOURCE),
                get(BITFINEX_TICKER_DATA_SOURCE),
                get(HUOBI_TICKER_DATA_SOURCE),
                get(COINEX_TICKER_DATA_SOURCE),
                get(HITBTC_TICKER_DATA_SOURCE)) as MainExchangeDataSource
    }
}