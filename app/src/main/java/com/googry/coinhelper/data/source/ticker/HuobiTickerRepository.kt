package com.googry.coinhelper.data.source.ticker

import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.ext.logE
import com.googry.coinhelper.ext.networkCommunication
import com.googry.coinhelper.network.api.HuobiApi
import com.googry.coinhelper.network.model.HuobiTickerResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class HuobiTickerRepository(private val huobiApi: HuobiApi)
    : TickerDataSource {

    companion object {
        private const val REQUEST_TIME_IN_MILLIS = 5000L

        private val compositeDisposable = CompositeDisposable()

        private var tickerBehaviorSubject: BehaviorSubject<List<HuobiTickerResponse.Data>>? = null

        private var subscribeCnt = 0
    }


    override fun getAllTicker(baseCurrency: String?,
                              success: (tickers: List<Ticker>) -> Unit,
                              failed: (errorCode: String) -> Unit): Disposable {
        if (tickerBehaviorSubject == null && subscribeCnt == 0) {
            logE("create")
            tickerBehaviorSubject = BehaviorSubject.create<List<HuobiTickerResponse.Data>>()
            compositeDisposable.add(Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                    .observeOn(Schedulers.newThread())
                    .subscribe {
                        huobiApi.getAllTickers()
                                .networkCommunication()
                                .subscribe({
                                    tickerBehaviorSubject?.onNext(it.data)
                                }) {
                                }
                    })
        }
        subscribeCnt += 1
        return tickerBehaviorSubject!!.map {
            it.filter {
                it.symbol.toUpperCase().endsWith(baseCurrency!!)
            }.map {
                it.toTicker().apply {
                    this.currency = it.symbol.toUpperCase().substring(0, it.symbol.length - baseCurrency?.length!!)
                    this.baseCurrency = baseCurrency
                }
            }
        }.subscribe({
            success.invoke(it)
        }) {
            failed.invoke("")
        }
    }

    override fun finish() {
        subscribeCnt -= 1
        if (tickerBehaviorSubject != null && subscribeCnt == 0) {
            logE("finish")
            compositeDisposable.clear()
            tickerBehaviorSubject = null
        }
    }
}