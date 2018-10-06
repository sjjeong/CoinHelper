package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.HitbtcTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HitbtcApi {

    @GET("api/1/public/ticker")
    fun getAllTickers(): Single<Map<String, HitbtcTickerResponse>>

    @GET("/api/1/public/{symbol}/ticker")
    fun getTicker(@Path("symbol") symbol: String): Single<HitbtcTickerResponse>
}