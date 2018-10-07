package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.HuobiAllTickerResponse
import com.googry.coinhelper.network.model.HuobiTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HuobiApi {
    @GET("market/tickers")
    fun getAllTickers(): Single<HuobiAllTickerResponse>

    @GET("market/detail/merged")
    fun getTicker(@Query("symbol") symbol: String): Single<HuobiTickerResponse>
}