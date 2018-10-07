package com.googry.coinhelper.network.api

import com.googry.coinhelper.network.model.LBankAllTickerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface LBankApi {

    @GET("v1/ticker.do?symbol=all")
    fun getAllTicker(): Single<List<LBankAllTickerResponse>>

    // symbol=eth_btc
    @GET("v1/ticker.do")
    fun getTicker(@Query("symbol") symbol: String): Single<LBankAllTickerResponse>

}