package com.googry.coinhelper.network.api

import io.reactivex.Single
import retrofit2.http.GET

interface CoinoneApi {

    @GET(value = "ticker/?currency=all")
    fun allTicker(): Single<Map<String, Any>>
}