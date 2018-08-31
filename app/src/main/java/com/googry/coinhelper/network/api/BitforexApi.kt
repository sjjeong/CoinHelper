package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.BitforexTickerResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BitforexApi {

    @GET(value = "server/market.act?cmd=searchTickers&type=all")
    fun getTickers(): Single<BitforexTickerResponse>
}