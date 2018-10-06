package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.BinanceTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BinanceApi {

    @GET("api/v1/ticker/24hr")
    fun getTickers(): Single<List<BinanceTickerResponse>>

    @GET("api/v1/ticker/24hr")
    fun getTicker(@Query("symbol") symbol: String): Single<BinanceTickerResponse>
}