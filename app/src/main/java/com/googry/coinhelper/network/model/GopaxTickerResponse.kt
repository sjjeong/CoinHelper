package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class GopaxTickerResponse(
        @SerializedName("name") val name: String,
        @SerializedName("open") val open: Double,
        @SerializedName("high") val high: Double,
        @SerializedName("low") val low: Double,
        @SerializedName("close") val close: Double,
        @SerializedName("volume") val volume: Double,
        @SerializedName("time") val time: String
) : ITicker {
    override fun toTicker():Ticker {
        val names = name.split("-")
        val diff = (close - open) / open
        return Ticker(
                currency = names[0],
                baseCurrency = names[1],
                last = close,
                high = high,
                low = low,
                diff = diff,
                volume = volume * close
        )
    }
}