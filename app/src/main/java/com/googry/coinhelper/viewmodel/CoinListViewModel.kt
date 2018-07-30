package com.googry.coinhelper.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.googry.coinhelper.base.ui.BaseViewModel
import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.data.source.TickerDataSource
import io.reactivex.disposables.Disposable

class CoinListViewModel(
        private val tickerDataSource: TickerDataSource
) : BaseViewModel() {

    val liveTickers = MutableLiveData<List<Ticker>>()

    fun getAllTickers(): Disposable =
            tickerDataSource.getAllTicker { exchange, tickers ->
                val list = tickers.values.map {
                    it.toTicker()
                }.sortedByDescending { it.volume * it.last }
                when (exchange) {
                    Exchange.COINONE -> liveTickers.postValue(list)
                }
            }

}