package com.googry.coinhelper.data.source

import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.source.ticker.TickerDataSource
import io.reactivex.disposables.CompositeDisposable

interface MainExchangeDataSource {

    fun saveMainExchange(exchange: Exchange)

    fun getSelectedExchange(): Exchange?

    fun getTickerDataSource(): TickerDataSource

    fun getMergeTicker(currency: String, baseCurrency: String? = "",
                       response: (exchangeTicker: ExchangeTicker) -> Unit): CompositeDisposable
}