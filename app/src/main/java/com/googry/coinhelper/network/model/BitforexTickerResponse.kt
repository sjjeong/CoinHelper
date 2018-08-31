package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class BitforexTickerResponse(
        @SerializedName("data") val data: Map<String, BitforexTicker>,
        @SerializedName("success") val success: Boolean,
        @SerializedName("time") val time: Long
) {
    data class BitforexTicker(
            @SerializedName("high") val high: Double,
            @SerializedName("vol") val vol: Double,
            @SerializedName("busitype") val busitype: String,
            @SerializedName("last") val last: Double,
            @SerializedName("low") val low: Double,
            @SerializedName("state") val state: Int,
            @SerializedName("float") val float: Double,
            @SerializedName("open") val open: Double,
            @SerializedName("cvol") val cvol: Double,
            @SerializedName("group") val group: String
    ) : ITicker {
        override fun toTicker(): Ticker {
            val names = busitype.split("-")
            return Ticker(baseCurrency = names[1].toUpperCase(),
                    currency = names[2].toUpperCase(),
                    last = last,
                    high = high,
                    low = low,
                    volume = cvol,
                    diff = float * 100
            )
        }
    }
}
