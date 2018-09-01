package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.BinanceTickerResponse
import com.googry.coinhelper.network.model.BitzTickerResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BitzApi {

    @GET(value = "Market/marketList?symbol=btc_dkkt")
    fun getTickers(): Single<BitzTickerResponse>
}