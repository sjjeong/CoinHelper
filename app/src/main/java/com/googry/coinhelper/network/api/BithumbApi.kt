package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.BithumbTickerResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BithumbApi {

    @GET(value = "public/ticker/all")
    fun allTicker(): Single<BithumbTickerResponse>
}