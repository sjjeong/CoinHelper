package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class BithumbTickerResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val data: Map<String, Any>
) {
    data class BithubTicker(
            @SerializedName("opening_price") val openingPrice: Double,
            @SerializedName("closing_price") val closingPrice: Double,
            @SerializedName("min_price") val minPrice: Double,
            @SerializedName("max_price") val maxPrice: Double,
            @SerializedName("average_price") val averagePrice: String,
            @SerializedName("units_traded") val unitsTraded: String,
            @SerializedName("volume_1day") val volume1day: Double,
            @SerializedName("volume_7day") val volume7day: Double,
            @SerializedName("buy_price") val buyPrice: Double,
            @SerializedName("sell_price") val sellPrice: Double,
            @SerializedName("24H_fluctate") val hFluctate: Int,
            @SerializedName("24H_fluctate_rate") val hFluctateRate: String
    ) : ITicker {
        override fun toTicker() =
                Ticker(currency = "",
                        last = closingPrice,
                        high = maxPrice,
                        low = minPrice,
                        first = openingPrice,
                        volume = volume1day * closingPrice)
    }
}