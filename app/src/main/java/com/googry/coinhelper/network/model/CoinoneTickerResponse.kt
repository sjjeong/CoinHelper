package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

const val COINONE_TICKER_FIELD_ERROR_CODE = "errorCode"
const val COINONE_TICKER_FIELD_TIMESTAMP = "timestamp"
const val COINONE_TICKER_FIELD_RESULT = "result"

data class CoinoneTicker(
        @SerializedName("volume") val volume: Double,
        @SerializedName("last") val last: Double,
        @SerializedName("yesterday_last") val yesterdayLast: String,
        @SerializedName("yesterday_low") val yesterdayLow: String,
        @SerializedName("high") val high: Double,
        @SerializedName("currency") val currency: String?,
        @SerializedName("low") val low: Double,
        @SerializedName("yesterday_first") val yesterdayFirst: String,
        @SerializedName("yesterday_volume") val yesterdayVolume: String,
        @SerializedName("yesterday_high") val yesterdayHigh: String,
        @SerializedName("first") val first: Double
) : ITicker {
    override fun toTicker(): Ticker {
        val diff = (last - first) / first * 100
        return Ticker(currency = currency ?: "",
                baseCurrency = "KRW",
                last = last,
                high = high,
                low = low,
                diff = diff,
                volume = volume * last)
    }

    override fun toExchangeTicker(exchange: String) = ExchangeTicker("Coinone", toTicker())
}

