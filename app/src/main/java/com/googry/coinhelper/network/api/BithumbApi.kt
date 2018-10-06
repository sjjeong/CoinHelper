package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.BithumbAllTickerResponse
import com.googry.coinhelper.network.model.BithumbTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface BithumbApi {

    @GET("public/ticker/all")
    fun getAllTicker(): Single<BithumbAllTickerResponse>


    @GET("public/ticker/{symbol}")
    fun getTicker(@Path("symbol") symbol: String): Single<BithumbTickerResponse>


}