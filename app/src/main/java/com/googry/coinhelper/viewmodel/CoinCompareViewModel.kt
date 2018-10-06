package com.googry.coinhelper.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.googry.coinhelper.base.ui.BaseViewModel
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.source.MainExchangeDataSource
import io.reactivex.disposables.CompositeDisposable

class CoinCompareViewModel(private val mainExchangeDataSource: MainExchangeDataSource)
    : BaseViewModel() {

    val liveCurrency = MutableLiveData<String>()

    val liveBaseCurrency = MutableLiveData<String>()

    val liveExchangeTickers = MutableLiveData<List<ExchangeTicker>>()

    val exchangeTickerMap = HashMap<String, ExchangeTicker>()

    fun getExchangeTickers(): CompositeDisposable? {
        return if (liveCurrency.value.isNullOrEmpty()) {
            null
        } else {
            mainExchangeDataSource.getMergeTicker(liveCurrency.value!!, liveBaseCurrency.value, response = {
                exchangeTickerMap[it.exchange] = it
                liveExchangeTickers.value = exchangeTickerMap.map {
                    it.value
                }
            })
        }
    }

}