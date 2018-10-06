package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class HuobiAllTickerResponse(
        @SerializedName("status") val status: String,
        @SerializedName("ts") val ts: Long,
        @SerializedName("data") val data: List<HuobiTicker>
) {
    data class HuobiTicker(
            @SerializedName("open") val open: Double,
            @SerializedName("close") val close: Double,
            @SerializedName("low") val low: Double,
            @SerializedName("high") val high: Double,
            @SerializedName("amount") val amount: Double,
            @SerializedName("count") val count: Int,
            @SerializedName("vol") val vol: Double,
            @SerializedName("symbol") val symbol: String
    ) : ITicker {
        override fun toTicker(): Ticker {
            val diff = (close - open) / open * 100
            return Ticker(
                    last = close,
                    high = high,
                    low = low,
                    diff = diff,
                    volume = vol * close)
        }

        override fun toExchangeTicker(exchange: String) = ExchangeTicker("Huobi", toTicker())

    }
}