package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.CoinoneTicker
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinoneApi {

    @GET("ticker/?currency=all")
    fun getAllTicker(): Single<Map<String, Any>>

    @GET("ticker/")
    fun getTicker(@Query("currency") currency: String): Single<CoinoneTicker>
}