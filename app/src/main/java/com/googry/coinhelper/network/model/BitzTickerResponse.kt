package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class BitzTickerResponse(
        @SerializedName("status") val status: Int,
        @SerializedName("msg") val msg: String,
        @SerializedName("data") val data: Data,
        @SerializedName("time") val time: Int,
        @SerializedName("microtime") val microtime: String,
        @SerializedName("source") val source: String
) {
    data class Data(
            @SerializedName("tickerAll") val tickerAll: Map<String, BitzTicker>
    ) {
        data class BitzTicker(
                @SerializedName("symbol") val symbol: String,
                @SerializedName("quoteVolume") val quoteVolume: Double,
                @SerializedName("volume") val volume: String,
                @SerializedName("priceChange") val priceChange: String,
                @SerializedName("priceChange24h") val priceChange24h: Double,
                @SerializedName("askPrice") val askPrice: String,
                @SerializedName("askQty") val askQty: String,
                @SerializedName("bidPrice") val bidPrice: String,
                @SerializedName("bidQty") val bidQty: String,
                @SerializedName("open") val open: Double,
                @SerializedName("high") val high: Double,
                @SerializedName("low") val low: Double,
                @SerializedName("now") val now: Double,
                @SerializedName("firstId") val firstId: String,
                @SerializedName("lastId") val lastId: String,
                @SerializedName("dealCount") val dealCount: String,
                @SerializedName("numberPrecision") val numberPrecision: Int,
                @SerializedName("pricePrecision") val pricePrecision: Int,
                @SerializedName("cny") val cny: String,
                @SerializedName("usd") val usd: String,
                @SerializedName("krw") val krw: String
        ) : ITicker {
            override fun toTicker(): Ticker {
                val names = symbol.toUpperCase().split("_")
                return Ticker(
                        currency = names[0],
                        baseCurrency = names[1],
                        last = now,
                        high = high,
                        low = low,
                        diff = priceChange24h,
                        volume = quoteVolume
                )
            }
        }
    }
}