package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

const val COINONE_TICKER_FIELD_ERROR_CODE = "errorCode"
const val COINONE_TICKER_FIELD_TIMESTAMP = "timestamp"
const val COINONE_TICKER_FIELD_RESULT = "result"

data class CoinoneTicker(
        @SerializedName("volume") val volume: String,
        @SerializedName("last") val last: String,
        @SerializedName("yesterday_last") val yesterdayLast: String,
        @SerializedName("yesterday_low") val yesterdayLow: String,
        @SerializedName("high") val high: String,
        @SerializedName("currency") val currency: String,
        @SerializedName("low") val low: String,
        @SerializedName("yesterday_first") val yesterdayFirst: String,
        @SerializedName("yesterday_volume") val yesterdayVolume: String,
        @SerializedName("yesterday_high") val yesterdayHigh: String,
        @SerializedName("first") val first: String
) : ITicker {
    override fun toTicker() =
            Ticker(currency = currency,
                    last = last.toDouble(),
                    high = high.toDouble(),
                    low = low.toDouble(),
                    first = first.toDouble(),
                    volume = volume.toDouble())

}

