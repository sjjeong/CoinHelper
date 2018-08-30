package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.GopaxTickerResponse
import io.reactivex.Single
import retrofit2.http.GET

interface GopaxApi {

    @GET(value = "trading-pairs/stats")
    fun getTickers(): Single<List<GopaxTickerResponse>>
}