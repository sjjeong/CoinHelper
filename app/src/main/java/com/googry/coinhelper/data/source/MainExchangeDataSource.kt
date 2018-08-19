package com.googry.coinhelper.data.source

import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.source.ticker.TickerDataSource

interface MainExchangeDataSource {

    fun saveMainExchange(exchange: Exchange)

    fun getSelectedExchange(): Exchange?

    fun getTickerDataSource(): TickerDataSource
}