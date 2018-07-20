package com.googry.coinhelper.data.source

import com.googry.coinhelper.data.enum.Market
import com.googry.coinhelper.data.model.ITicker
import io.reactivex.disposables.Disposable

interface TickerDataSource {
    fun getAllTicker(response: (market: String, tickers: Map<String, ITicker>) -> (Any)): Disposable
}