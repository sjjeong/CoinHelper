package com.googry.coinhelper.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.googry.coinhelper.base.ui.BaseViewModel
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.data.source.ticker.TickerDataSource
import io.reactivex.disposables.Disposable

class CoinListViewModel(private val tickerDataSource: TickerDataSource)
    : BaseViewModel() {

    val liveTickers = MutableLiveData<List<Ticker>>()

    var baseCurrency: String? = null

    fun getAllTickers(): Disposable =
            tickerDataSource.getAllTicker(baseCurrency, success = {
                liveTickers.postValue(it.sortedByDescending { it.last * it.volume })
            }, failed = {

            })

}