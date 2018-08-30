package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.BinanceTickerResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BinanceApi {

    @GET(value = "api/v1/ticker/24hr")
    fun getTickers(): Single<List<BinanceTickerResponse>>
}