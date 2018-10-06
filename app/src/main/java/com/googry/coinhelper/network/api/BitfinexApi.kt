package com.googry.coinhelper.network.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BitfinexApi {

    @GET("v2/tickers?symbols=ALL")
    fun getAllTickers(): Single<List<List<String>>>

    @GET("v2/ticker/{symbol}")
    fun getTicker(@Path("symbol") symbol: String): Single<List<String>>
}