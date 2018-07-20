package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker


data class CoinoneTicker(
        val volume: String,
        val last: String,
        @SerializedName(value = "yesterday_last")
        val yesterdayLast: String,
        @SerializedName(value = "yesterday_low")
        val yesterdayLow: String,
        val high: String,
        val currency: String,
        val low: String,
        @SerializedName(value = "yesterday_first")
        val yesterdayFirst: String,
        @SerializedName(value = "yesterday_volume")
        val yesterdayVolume: String,
        @SerializedName(value = "yesterday_high")
        val yesterdayHigh: String,
        val first: String
) : ITicker {

    override fun toTicker() = Ticker(
            currency = currency,
            last = last.toDouble(),
            high = high.toDouble(),
            low = low.toDouble(),
            first = first.toDouble(),
            volume = volume.toDouble()
    )

}