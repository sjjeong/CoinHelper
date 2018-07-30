package com.googry.coinhelper.data.source

import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.model.ITicker
import io.reactivex.disposables.Disposable

interface TickerDataSource {
    fun getAllTicker(response: (exchange: Exchange, tickers: Map<String, ITicker>) -> (Any)): Disposable
}