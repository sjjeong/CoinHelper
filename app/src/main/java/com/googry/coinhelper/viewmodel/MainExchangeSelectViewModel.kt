package com.googry.coinhelper.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.googry.coinhelper.base.ui.BaseViewModel
import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.source.MainExchangeDataSource

class MainExchangeSelectViewModel(
        val mainExchangeDataSource: MainExchangeDataSource
) : BaseViewModel() {

    val liveExchanges = MutableLiveData<List<Exchange>>()

    var selectedItem = -1

    init {
        liveExchanges.postValue(enumValues<Exchange>().toList())
    }

    fun saveMainExchange(): Boolean {
        if (selectedItem == -1) {
            return false
        }
        mainExchangeDataSource.saveMainExchange(Exchange.values()[selectedItem])
        return true
    }

    fun getMainExchange(response: (exchange: Exchange?) -> Unit) {
        mainExchangeDataSource.loadMainExchange {
            response.invoke(it)
        }
    }


}