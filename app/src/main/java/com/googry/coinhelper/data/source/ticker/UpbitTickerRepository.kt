package com.googry.coinhelper.data.source.ticker

import com.google.gson.Gson
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.ext.fromJson
import com.googry.coinhelper.ext.networkCommunication
import com.googry.coinhelper.network.api.CoinoneApi
import com.googry.coinhelper.network.api.UpbitApi
import com.googry.coinhelper.network.model.COINONE_TICKER_FIELD_ERROR_CODE
import com.googry.coinhelper.network.model.COINONE_TICKER_FIELD_RESULT
import com.googry.coinhelper.network.model.COINONE_TICKER_FIELD_TIMESTAMP
import com.googry.coinhelper.network.model.CoinoneTicker
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class UpbitTickerRepository(private val upbitApi: UpbitApi)
    : TickerDataSource {

    private val REQUEST_TIME_IN_MILLIS = 5000L

    override fun getAllTicker(baseCurrency: String?,
                              success: (tickers: List<Ticker>) -> Unit,
                              failed: (errorCode: String) -> Unit): Disposable {
        return Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe {
                    upbitApi.getMarkets()
                            .networkCommunication()
                            .map {
                                it.filter {
                                    it.market.startsWith(baseCurrency!!.toUpperCase())
                                }.map {
                                    it.market
                                }.joinToString(",")
                            }
                            .subscribe({
                                upbitApi.getTickers(it)
                                        .networkCommunication()
                                        .map {
                                            it.map {
                                                it.toTicker()
                                            }
                                        }
                                        .subscribe({
                                            success.invoke(it)
                                        }){
                                            failed.invoke("")
                                        }
                            }){
                                failed.invoke("")
                            }
                }
    }
}