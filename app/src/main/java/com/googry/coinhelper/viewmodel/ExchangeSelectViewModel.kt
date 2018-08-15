package com.googry.coinhelper.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.googry.coinhelper.base.ui.BaseViewModel
import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.source.MainExchangeDataSource

class ExchangeSelectViewModel(
        val mainExchangeDataSource: MainExchangeDataSource
) : BaseViewModel() {

    val liveExchanges = MutableLiveData<List<Exchange>>()

    var selectedItemPosition = -1

    init {
        val exchanges = Exchange.values()
        liveExchanges.postValue(exchanges.toList())
        selectedItemPosition = exchanges.indexOf(mainExchangeDataSource.getSelectedExchange())
    }

    fun saveMainExchange(): Boolean {
        if (selectedItemPosition == -1) {
            return false
        }
        mainExchangeDataSource.saveMainExchange(Exchange.values()[selectedItemPosition])
        return true
    }

    fun getBaseCurrencies() = mainExchangeDataSource.getSelectedExchange()?.baseCurrencies!!

    fun getSelectedExchange() = mainExchangeDataSource.getSelectedExchange()!!

}