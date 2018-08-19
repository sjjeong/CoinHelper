package com.googry.coinhelper.data.source

import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.source.ticker.TickerDataSource
import com.googry.coinhelper.data.source.ticker.TickerRepository
import com.googry.coinhelper.util.PrefUtils

class MainExchangeRepository(private val prefUtils: PrefUtils,
                             private val coinoneDataSource: TickerDataSource,
                             private val upbitDataSource: TickerDataSource,
                             private val bithumbDataSource: TickerDataSource)
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
                else -> error("Unknown Exchange")

            }
}