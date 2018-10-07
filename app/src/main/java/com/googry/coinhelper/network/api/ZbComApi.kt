package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.ZbComAllTickerResponse
import com.googry.coinhelper.network.model.ZbComTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ZbComApi {
    @GET("data/v1/allTicker")
    fun getAllTicker(): Single<Map<String, ZbComAllTickerResponse.ZbComAllTicker>>

    // currency_baseCurrency
    @GET("data/v1/ticker")
    fun getTicker(@Query("market") symbol: String): Single<ZbComTickerResponse>
}