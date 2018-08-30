package com.googry.coinhelper.network.api

import io.reactivex.Single
import retrofit2.http.GET

interface BitfinexApi {

    @GET("v2/tickers?symbols=ALL")
    fun getAllTickers(): Single<List<List<String>>>
}