package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class BithumbTickerResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val data: Map<String, Any>
) {
    data class BithubTicker(
            @SerializedName("opening_price") val openingPrice: String,
            @SerializedName("closing_price") val closingPrice: String,
            @SerializedName("min_price") val minPrice: String,
            @SerializedName("max_price") val maxPrice: String,
            @SerializedName("average_price") val averagePrice: String,
            @SerializedName("units_traded") val unitsTraded: String,
            @SerializedName("volume_1day") val volume1day: String,
            @SerializedName("volume_7day") val volume7day: String,
            @SerializedName("buy_price") val buyPrice: String,
            @SerializedName("sell_price") val sellPrice: String,
            @SerializedName("24H_fluctate") val hFluctate: Int,
            @SerializedName("24H_fluctate_rate") val hFluctateRate: String
    ) : ITicker {
        override fun toTicker() =
                Ticker(currency = "",
                        last = closingPrice.toDouble(),
                        high = maxPrice.toDouble(),
                        low = minPrice.toDouble(),
                        first = openingPrice.toDouble(),
                        volume = volume1day.toDouble())
    }
}