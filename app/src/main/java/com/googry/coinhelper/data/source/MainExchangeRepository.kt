package com.googry.coinhelper.data.source

import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.source.ticker.TickerDataSource
import com.googry.coinhelper.util.PrefUtils

class MainExchangeRepository(private val prefUtils: PrefUtils,
                             private val coinoneDataSource: TickerDataSource,
                             private val upbitDataSource: TickerDataSource,
                             private val bithumbDataSource: TickerDataSource,
                             private val gopaxDataSource: TickerDataSource,
                             private val binanceDataSource: TickerDataSource,
                             private val bitfinexDataSource: TickerDataSource,
                             private val huobiDataSource: TickerDataSource,
                             private val coinexDataSource: TickerDataSource)
    : MainExchangeDataSource {

    override fun saveMainExchange(exchange: Exchange) {
        prefUtils.saveExchange(exchange)
    }

    override fun getSelectedExchange() = prefUtils.getExchange()

    override fun getTickerDataSource() =
            when (getSelectedExchange()) {
                Exchange.COINONE -> coinoneDataSource
                Exchange.UPBIT -> upbitDataSource
                Exchange.BITHUMB -> bithumbDataSource
                Exchange.GOPAX -> gopaxDataSource
                Exchange.BINANCE -> binanceDataSource
                Exchange.BITFINEX -> bitfinexDataSource
                Exchange.HUOBI -> huobiDataSource
                Exchange.COINEX -> coinexDataSource
                else -> error("Unknown Exchange")

            }
}