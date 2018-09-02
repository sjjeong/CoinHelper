package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class HitbtcTickerResponse(
        @SerializedName("ask") val ask: String?,
        @SerializedName("bid") val bid: String?,
        @SerializedName("last") var last: String?,
        @SerializedName("low") var low: String?,
        @SerializedName("high") var high: String?,
        @SerializedName("open") var open: String?,
        @SerializedName("volume") val volume: String?,
        @SerializedName("volume_quote") val volumeQuote: String?,
        @SerializedName("timestamp") val timestamp: Long
) : ITicker {
    override fun toTicker(): Ticker {
        if (last.isNullOrEmpty()) {
            last = "0.0"
        }
        if (open.isNullOrEmpty()) {
            open = "1.0"
        }
        if (low.isNullOrEmpty()) {
            low = "0.0"
        }
        if (high.isNullOrEmpty()) {
            high = "0.0"
        }
        val diff = (last?.toDouble()!! - open?.toDouble()!!) / open?.toDouble()!! * 100
        return Ticker(last = last?.toDouble()!!,
                high = high?.toDouble()!!,
                low = low?.toDouble()!!,
                diff = diff,
                volume = volumeQuote?.toDouble()!!)
    }
}