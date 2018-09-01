package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.CoinexTickerResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CoinexApi {

    @GET(value = "v1/market/ticker/all")
    fun getTickers(): Single<CoinexTickerResponse>
}