package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.CoinoneAllTicker
import com.googry.coinhelper.network.model.CoinoneTicker
import io.reactivex.Single
import retrofit2.http.GET

interface CoinoneApi {

    @GET(value = "ticker/?currency=all")
    fun allTicker(): Single<CoinoneAllTicker>
}