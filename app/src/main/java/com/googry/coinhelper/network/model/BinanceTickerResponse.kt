package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class BinanceTickerResponse(
        @SerializedName("symbol") val symbol: String,
        @SerializedName("priceChange") val priceChange: Double,
        @SerializedName("priceChangePercent") val priceChangePercent: Double,
        @SerializedName("weightedAvgPrice") val weightedAvgPrice: Double,
        @SerializedName("prevClosePrice") val prevClosePrice: Double,
        @SerializedName("lastPrice") val lastPrice: Double,
        @SerializedName("lastQty") val lastQty: Double,
        @SerializedName("bidPrice") val bidPrice: Double,
        @SerializedName("bidQty") val bidQty: Double,
        @SerializedName("askPrice") val askPrice: Double,
        @SerializedName("askQty") val askQty: Double,
        @SerializedName("openPrice") val openPrice: Double,
        @SerializedName("highPrice") val highPrice: Double,
        @SerializedName("lowPrice") val lowPrice: Double,
        @SerializedName("volume") val volume: Double,
        @SerializedName("quoteVolume") val quoteVolume: Double,
        @SerializedName("openTime") val openTime: Long,
        @SerializedName("closeTime") val closeTime: Long,
        @SerializedName("firstId") val firstId: Int,
        @SerializedName("lastId") val lastId: Int,
        @SerializedName("count") val count: Int
) : ITicker {
    override fun toTicker() = Ticker(
            last = lastPrice,
            high = highPrice,
            low = lowPrice,
            diff = priceChangePercent,
            volume = quoteVolume
    )
}