package com.googry.coinhelper.data.source.ticker

import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.ext.logE
import com.googry.coinhelper.ext.networkCommunication
import com.googry.coinhelper.network.api.BitfinexApi
import com.googry.coinhelper.network.model.BitfinexAllTickerResponse
import com.googry.coinhelper.network.model.BitfinexTickerResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class BitfinexTickerRepository(private val bitfinexApi: BitfinexApi)
    : TickerDataSource {

    companion object {
        private const val REQUEST_TIME_IN_MILLIS = 5000L

        private val compositeDisposable = CompositeDisposable()

        private var sAllTickerBehaviorSubject: BehaviorSubject<List<BitfinexAllTickerResponse>>? = null

        private var subscribeCnt = 0
    }


    override fun getAllTicker(baseCurrency: String?,
                              success: (tickers: List<Ticker>) -> Unit,
                              failed: (errorCode: String) -> Unit): Disposable {
        if (sAllTickerBehaviorSubject == null && subscribeCnt == 0) {
            logE("create")
            sAllTickerBehaviorSubject = BehaviorSubject.create<List<BitfinexAllTickerResponse>>()
            compositeDisposable.add(Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                    .observeOn(Schedulers.newThread())
                    .subscribe {
                        bitfinexApi.getAllTickers()
                                .networkCommunication()
                                .map {
                                    it.map {
                                        BitfinexAllTickerResponse(it)
                                    }
                                }
                                .subscribe({
                                    sAllTickerBehaviorSubject?.onNext(it)
                                }) {
                                }
                    })
        }
        subscribeCnt += 1
        return sAllTickerBehaviorSubject!!
                .map {
                    it.filter {
                        !it.data[BitfinexAllTickerResponse.SYMBOL_POS].startsWith("f")
                    }.filter {
                        it.data[BitfinexAllTickerResponse.SYMBOL_POS].endsWith(baseCurrency?.toUpperCase()!!)
                    }.map {
                        it.toTicker().apply {
                            val symbol = it.data[BitfinexAllTickerResponse.SYMBOL_POS]
                            this.currency = symbol.substring(1, symbol.length - baseCurrency?.length!!)
                            this.baseCurrency = baseCurrency
                        }
                    }
                }
                .subscribe({
                    success.invoke(it)
                }) {
                    failed.invoke("")
                }
    }

    override fun finish() {
        subscribeCnt -= 1
        if (sAllTickerBehaviorSubject != null && subscribeCnt == 0) {
            logE("finish")
            compositeDisposable.clear()
            sAllTickerBehaviorSubject = null
        }
    }

    override fun getTicker(currency: String, baseCurrency: String?,
                           success: (ticker: ExchangeTicker) -> Unit,
                           failed: (errorCode: String) -> Unit): Disposable {
        return Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe {
                    bitfinexApi.getTicker("t$currency$baseCurrency")
                            .networkCommunication()
                            .subscribe({
                                success.invoke(BitfinexTickerResponse(it).toExchangeTicker().also {
                                    it.currency = currency
                                    it.baseCurrency = baseCurrency!!
                                })
                            }) {

                            }
                }
    }

}