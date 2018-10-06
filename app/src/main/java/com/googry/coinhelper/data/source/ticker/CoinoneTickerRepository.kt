package com.googry.coinhelper.data.source.ticker

import com.google.gson.Gson
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.ext.fromJson
import com.googry.coinhelper.ext.networkCommunication
import com.googry.coinhelper.network.api.CoinoneApi
import com.googry.coinhelper.network.model.COINONE_TICKER_FIELD_ERROR_CODE
import com.googry.coinhelper.network.model.COINONE_TICKER_FIELD_RESULT
import com.googry.coinhelper.network.model.COINONE_TICKER_FIELD_TIMESTAMP
import com.googry.coinhelper.network.model.CoinoneTicker
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinoneTickerRepository(private val coinoneApi: CoinoneApi)
    : TickerDataSource {

    private val REQUEST_TIME_IN_MILLIS = 5000L

    override fun getAllTicker(baseCurrency: String?,
                              success: (tickers: List<Ticker>) -> Unit,
                              failed: (errorCode: String) -> Unit): Disposable {
        return Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe {
                    coinoneApi.getAllTicker()
                            .networkCommunication()
                            .doOnSuccess {
                                if (it[COINONE_TICKER_FIELD_ERROR_CODE] != "0") {
                                    failed.invoke("")
                                }
                            }
                            .filter {
                                it[COINONE_TICKER_FIELD_ERROR_CODE] == "0"
                            }
                            .map {
                                val gson = Gson()
                                it.filter {
                                    !mutableListOf(COINONE_TICKER_FIELD_ERROR_CODE,
                                            COINONE_TICKER_FIELD_TIMESTAMP,
                                            COINONE_TICKER_FIELD_RESULT).contains(it.key)
                                }.map { tickerMap ->
                                    gson.fromJson<CoinoneTicker>(tickerMap.value.toString())
                                            .toTicker()
                                }
                            }
                            .subscribe({
                                success.invoke(it)
                            }) {
                            }
                }
    }

    override fun finish() {

    }

    override fun getTicker(currency: String, baseCurrency: String?,
                           success: (ticker: ExchangeTicker) -> Unit,
                           failed: (errorCode: String) -> Unit): Disposable {
        return Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .subscribe {
                    if (baseCurrency == "KRW") {
                        coinoneApi.getTicker(currency)
                                .networkCommunication()
                                .subscribe({
                                    if (!it.currency.isNullOrEmpty()) {
                                        success.invoke(it.toExchangeTicker())
                                    }
                                }) {

                                }
                    }
                }

    }
}