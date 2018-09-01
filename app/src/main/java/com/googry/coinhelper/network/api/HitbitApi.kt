package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.HitbitTickerResponse
import io.reactivex.Single
import retrofit2.http.GET

interface HitbitApi {

    @GET("api/1/public/ticker")
    fun getAllTickers(): Single<Map<String, HitbitTickerResponse>>
}