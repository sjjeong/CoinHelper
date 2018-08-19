package com.googry.coinhelper.data.source.ticker

import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.data.source.MainExchangeDataSource
import io.reactivex.disposables.Disposable

class TickerRepository(private val mainExchangeDataSource: MainExchangeDataSource)
    : TickerDataSource {
    override fun getAllTicker(baseCurrency: String?,
                              success: (tickers: List<Ticker>) -> Unit,
                              failed: (errorCode: String) -> Unit): Disposable =
            mainExchangeDataSource
                    .getTickerDataSource()
                    .getAllTicker(baseCurrency, success, failed)


}