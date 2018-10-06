package com.googry.coinhelper.data.source.ticker

import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.ext.logE
import com.googry.coinhelper.ext.networkCommunication
import com.googry.coinhelper.network.api.HitbtcApi
import com.googry.coinhelper.network.model.HitbtcTickerResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class HitbtcTickerRepository(private val hitbtcApi: HitbtcApi)
    : TickerDataSource {

    companion object {
        private const val REQUEST_TIME_IN_MILLIS = 5000L

        private val compositeDisposable = CompositeDisposable()

        private var tickerBehaviorSubject: BehaviorSubject<Map<String, HitbtcTickerResponse>>? = null

        private var subscribeCnt = 0
    }


    override fun getAllTicker(baseCurrency: String?,
                              success: (tickers: List<Ticker>) -> Unit,
                              failed: (errorCode: String) -> Unit): Disposable {
        if (tickerBehaviorSubject == null && subscribeCnt == 0) {
            logE("create")
            tickerBehaviorSubject = BehaviorSubject.create<Map<String, HitbtcTickerResponse>>()
            compositeDisposable.add(Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                    .observeOn(Schedulers.newThread())
                    .subscribe {
                        hitbtcApi.getAllTickers()
                                .networkCommunication()
                                .subscribe({
                                    tickerBehaviorSubject?.onNext(it)
                                }) {
                                }
                    })
        }
        subscribeCnt += 1
        return tickerBehaviorSubject!!
                .map {
                    it.filter {
                        it.key.endsWith(baseCurrency!!)
                    }.map {
                        it.value.toTicker().apply {
                            this.currency = it.key.substring(0, it.key.length - baseCurrency?.length!!)
                            this.baseCurrency = baseCurrency
                        }
                    }.toList()
                }
                .subscribe({
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

    override fun getTicker(currency: String, baseCurrency: String?,
                           success: (ticker: ExchangeTicker) -> Unit,
                           failed: (errorCode: String) -> Unit): Disposable {
        return Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe {
                    hitbtcApi.getTicker(currency + baseCurrency)
                            .networkCommunication()
                            .subscribe({
                                success.invoke(it.toExchangeTicker())
                            }) {

                            }
                }
    }

}