package com.googry.coinhelper.data.source

import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.source.ticker.TickerDataSource
import com.googry.coinhelper.util.PrefUtils
import io.reactivex.disposables.CompositeDisposable

class MainExchangeRepository(private val prefUtils: PrefUtils,
                             private val tickerDataSourceMap: Map<String, TickerDataSource>)
    : MainExchangeDataSource {

    override fun saveMainExchange(exchange: Exchange) {
        prefUtils.saveExchange(exchange)
    }

    override fun getSelectedExchange() = prefUtils.getExchange()

    override fun getTickerDataSource() = tickerDataSourceMap[getSelectedExchange()!!.name]!!

    override fun getMergeTicker(currency: String, baseCurrency: String?,
                                response: (exchangeTicker: ExchangeTicker) -> Unit): CompositeDisposable {
        val compositeDisposable = CompositeDisposable()
        tickerDataSourceMap.forEach { t, u ->
            compositeDisposable.add(u.getTicker(currency, baseCurrency, success = {
                response.invoke(it)
            }, failed = {

            }))
        }
        return compositeDisposable
    }
}