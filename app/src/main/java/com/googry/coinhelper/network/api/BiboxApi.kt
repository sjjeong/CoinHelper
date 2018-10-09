package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.BiboxAllTickerResponse
import com.googry.coinhelper.network.model.BiboxTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BiboxApi {

    @GET("v1/mdata?cmd=marketAll")
    fun getAllTicker(): Single<BiboxAllTickerResponse>

    // pair=ETH_BTC
    @GET("v1/mdata?cmd=market")
    fun getTicker(@Query("pair") pair: String): Single<BiboxTickerResponse>
}