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

    var liveIsDescending = MutableLiveData<Boolean>().apply {
        value = true
    }

    var liveSelectedSortItem = MutableLiveData<String>().apply {
        value = "last"
    }

    fun getExchangeTickers(): CompositeDisposable? {
        return if (liveCurrency.value.isNullOrEmpty()) {
            null
        } else {
            mainExchangeDataSource.getMergeTicker(liveCurrency.value!!, liveBaseCurrency.value, response = {
                exchangeTickerMap[it.exchange] = it
                liveExchangeTickers.value = exchangeTickerMap.map {
                    it.value
                }
                sortExchangeTicker()
            })
        }
    }

    fun onSortClick(item: String) {
        if (item == liveSelectedSortItem.value) {
            liveIsDescending.value = (!liveIsDescending.value!!)
        } else {
            liveSelectedSortItem.value = (item)
        }

        sortExchangeTicker()
    }

    private fun sortExchangeTicker(){
        liveExchangeTickers.postValue(
                if (liveIsDescending.value!!) {
                    when (liveSelectedSortItem.value!!) {
                        "exchange" -> liveExchangeTickers.value?.sortedByDescending {
                            it.exchange
                        }
                        "last" -> liveExchangeTickers.value?.sortedByDescending {
                            it.last
                        }
                        "diff" -> liveExchangeTickers.value?.sortedByDescending {
                            it.diff
                        }
                        else -> liveExchangeTickers.value?.sortedByDescending {
                            it.volume
                        }
                    }
                } else {
                    when (liveSelectedSortItem.value!!) {
                        "exchange" -> liveExchangeTickers.value?.sortedBy {
                            it.exchange
                        }
                        "last" -> liveExchangeTickers.value?.sortedBy {
                            it.last
                        }
                        "diff" -> liveExchangeTickers.value?.sortedBy {
                            it.diff
                        }
                        else -> liveExchangeTickers.value?.sortedBy {
                            it.volume
                        }
                    }

                })
    }

}