package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName

data class CoinExTickerResponse(
        @SerializedName("code") val code: Int,
        @SerializedName("data") val data: Data,
        @SerializedName("message") val message: String
) {
    data class Data(
            @SerializedName("date") val date: Long,
            @SerializedName("ticker") val ticker: CoinExAllTickerResponse.Data.CoinExTicker
    )
}