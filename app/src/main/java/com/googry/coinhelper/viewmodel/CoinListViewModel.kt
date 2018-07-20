package com.googry.coinhelper.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.googry.coinhelper.base.ui.BaseViewModel
import com.googry.coinhelper.data.enum.Market
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.source.TickerDataSource
import io.reactivex.disposables.Disposable

class CoinListViewModel(
        private val tickerDataSource: TickerDataSource
) : BaseViewModel() {

    val liveTickers = MutableLiveData<List<ITicker>>()

    fun getAllTickers(): Disposable =
        tickerDataSource.getAllTicker { market, tickers ->
            when (market) {
                Market.COINONE.marketName -> liveTickers.postValue(ArrayList<ITicker>(tickers.values))
            }
        }

}