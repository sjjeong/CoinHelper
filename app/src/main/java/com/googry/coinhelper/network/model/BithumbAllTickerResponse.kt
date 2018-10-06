package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class BithumbAllTickerResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val data: Map<String, Any>
) {
    data class BithumbTicker(
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
            @SerializedName("24H_fluctate_rate") val hFluctateRate: Double
    ) : ITicker {
        override fun toTicker() =
                Ticker(baseCurrency = "KRW",
                        last = closingPrice,
                        high = maxPrice,
                        low = minPrice,
                        diff = hFluctateRate,
                        volume = volume1day * closingPrice)

        override fun toExchangeTicker(exchange: String) = ExchangeTicker("Bithumb", toTicker())

    }
}