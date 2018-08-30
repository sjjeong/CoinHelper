package com.googry.coinhelper.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.googry.coinhelper.base.ui.BaseViewModel
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.data.source.MainExchangeDataSource
import com.googry.coinhelper.data.source.ticker.TickerDataSource
import com.googry.coinhelper.ext.logE
import io.reactivex.disposables.Disposable

class CoinListViewModel(private val mainExchangeDataSource: MainExchangeDataSource)
    : BaseViewModel() {

    private val exchangeTickerDataSource = mainExchangeDataSource.getTickerDataSource()

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
            exchangeTickerDataSource.getAllTicker(baseCurrency, success = {
                sortTickers(it)
            }, failed = {

            })

    fun finish(){
        exchangeTickerDataSource.finish()
    }

    private fun sortTickers(tickers: List<Ticker>) {
        liveTickers.postValue(
                if (coinSortViewModel?.liveIsDescending?.value!!) {
                    when (coinSortViewModel?.liveSelectedSortItem?.value!!) {
                        "currency" -> tickers.sortedByDescending {
                            it.currency
                        }
                        "last" -> tickers.sortedByDescending {
                            it.last
                        }
                        "diff" -> tickers.sortedByDescending {
                            (it.last - it.first) / it.first
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
                        "diff" -> tickers.sortedBy {
                            (it.last - it.first) / it.first
                        }
                        else -> tickers.sortedBy {
                            it.volume
                        }
                    }

                })
    }

}