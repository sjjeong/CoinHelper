package com.googry.coinhelper.data.source

import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.network.api.CoinoneApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TickerRepository(val coinoneApi: CoinoneApi) : TickerDataSource {

    private val REQUEST_TIME_IN_MILLIS = 2000L

    override fun getAllTicker(response: (exchange: Exchange, tickers: Map<String, ITicker>) -> Any)
            : Disposable =
            Observable.interval(0, REQUEST_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
                    .observeOn(Schedulers.newThread())
                    .subscribe {
                        coinoneApi.allTicker()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.newThread())
                                .subscribe { it -> response(Exchange.COINONE, it.toMap()) }

                    }

}