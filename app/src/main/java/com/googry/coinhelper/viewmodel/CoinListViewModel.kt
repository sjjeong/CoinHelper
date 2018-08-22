package com.googry.coinhelper.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.googry.coinhelper.base.ui.BaseViewModel
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.data.source.ticker.TickerDataSource
import io.reactivex.disposables.Disposable

class CoinListViewModel(private val tickerDataSource: TickerDataSource)
    : BaseViewModel() {

    var coinSortViewModel: CoinSortViewModel? = null
        set(value) {
            compositeDisposable.add(value!!.sortEventSubject!!.subscribe {
                liveTickers.value?.let {
                    sortTickers(it)
                }
            }!!)
            field = value
        }

    val liveTickers = MutableLiveData<List<Ticker>>()

    var baseCurrency: String? = null

    fun getAllTickers(): Disposable =
            tickerDataSource.getAllTicker(baseCurrency, success = {
                sortTickers(it)
            }, failed = {

            })

    private fun sortTickers(tickers: List<Ticker>) {
        Log.e("googry","${coinSortViewModel?.liveSelectedSortItem?.value!!} ${coinSortViewModel?.liveIsDescending?.value!!}")
        liveTickers.postValue(
                if (coinSortViewModel?.liveIsDescending?.value!!) {
                    when (coinSortViewModel?.liveSelectedSortItem?.value!!) {
                        "currency" -> tickers.sortedByDescending {
                            it.currency
                        }
                        "last" -> tickers.sortedByDescending {
                            it.last
                        }
                        else -> tickers.sortedByDescending {
                            it.volume
                        }
                    }
                } else {
                    when (coinSortViewModel?.liveSelectedSortItem?.value!!) {
                        "currency" -> tickers.sortedBy {
                            it.currency
                        }
                        "last" -> tickers.sortedBy {
                            it.last
                        }
                        else -> tickers.sortedBy {
                            it.volume
                        }
                    }

                })
    }

}