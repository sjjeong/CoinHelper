package com.googry.coinhelper.data.source.ticker

import com.google.gson.Gson
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.ext.fromJson
import com.googry.coinhelper.ext.networkCommunication
import com.googry.coinhelper.network.api.BithumbApi
import com.googry.coinhelper.network.model.BithumbTickerResponse
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BithumbTickerRepository(private val bithumbApi: BithumbApi)
    : TickerDataSource {

    private val REQUEST_TIME_IN_MILLIS = 5000L

    override fun getAllTicker(baseCurrency: String?,
                              success: (tickers: List<Ticker>) -> Unit,
                              failed: (errorCode: String) -> Unit): Disposable {
        return Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe {
                    bithumbApi.allTicker()
                            .networkCommunication()
                            .doOnSuccess {
                                if (it.status != "0000") {
                                    failed.invoke("")
                                }
                            }
                            .filter {
                                it.status == "0000"
                            }
                            .map {
                                val gson = Gson()
                                it.data.filter {
                                    it.key != "date"
                                }.map { tickerMap ->
                                    gson.fromJson<BithumbTickerResponse.BithubTicker>(tickerMap.value.toString())
                                            .toTicker().apply {
                                                currency = tickerMap.key
                                            }
                                }
                            }
                            .subscribe({
                                success.invoke(it)
                            }) {
                                failed.invoke("")
                            }

                }
    }

    override fun finish() {

    }
}