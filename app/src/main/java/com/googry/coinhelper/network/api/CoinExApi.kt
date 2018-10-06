package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.CoinExAllTickerResponse
import com.googry.coinhelper.network.model.CoinExTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinExApi {

    @GET("v1/market/ticker/all")
    fun getTickers(): Single<CoinExAllTickerResponse>

    @GET("v1/market/ticker")
    fun getTicker(@Query("market") market: String): Single<CoinExTickerResponse>
}