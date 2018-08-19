package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.UpbitMarketResponse
import com.googry.coinhelper.network.model.UpbitTicker
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpbitApi {

    @GET(value = "market/all")
    fun getMarkets(): Single<List<UpbitMarketResponse>>

    @GET(value = "ticker")
    fun getTickers(@Query("markets") markets: String): Single<List<UpbitTicker>>
}