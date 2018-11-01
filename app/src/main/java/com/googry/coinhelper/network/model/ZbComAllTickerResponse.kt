package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

class ZbComAllTickerResponse {
    data class ZbComAllTicker(
            @SerializedName("vol") val vol: Double,
            @SerializedName("last") val last: Double,
            @SerializedName("sell") val sell: String,
            @SerializedName("buy") val buy: String,
            @SerializedName("high") val high: Double,
            @SerializedName("low") val low: Double
    ) : ITicker {
        override fun toTicker() = Ticker(
                last = last,
                high = high,
                low = low,
                volume = vol * last
        )

        override fun toExchangeTicker(exchange: String) = ExchangeTicker(Exchange.ZBCOM.exchangeName, toTicker())
    }

}