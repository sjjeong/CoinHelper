package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.HuobiTickerResponse
import io.reactivex.Single
import retrofit2.http.GET

interface HuobiApi {
    @GET("market/tickers")
    fun getAllTickers(): Single<HuobiTickerResponse>
}