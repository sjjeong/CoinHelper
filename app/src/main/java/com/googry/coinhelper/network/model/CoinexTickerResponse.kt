package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class CoinexTickerResponse(
        @SerializedName("code") val code: Int,
        @SerializedName("data") val data: Data,
        @SerializedName("message") val message: String
) {
    data class Data(
            @SerializedName("date") val date: Long,
            @SerializedName("ticker") val ticker: Map<String, CoinexTicker>
    ) {
        data class CoinexTicker(
                @SerializedName("buy") val buy: Double,
                @SerializedName("buy_amount") val buyAmount: Double,
                @SerializedName("sell") val sell: Double,
                @SerializedName("sell_amount") val sellAmount: Double,
                @SerializedName("vol") val vol: Double,
                @SerializedName("low") val low: Double,
                @SerializedName("open") val open: Double,
                @SerializedName("high") val high: Double,
                @SerializedName("last") val last: Double
        ) : ITicker {
            override fun toTicker(): Ticker {
                val diff = (last - open) / open * 100
                return Ticker(
                        last = last,
                        high = high,
                        low = low,
                        diff = diff,
                        volume = vol * last

                )
            }
        }
    }
}