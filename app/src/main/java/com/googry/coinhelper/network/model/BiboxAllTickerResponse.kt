package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class BiboxAllTickerResponse(
        @SerializedName("result") val result: List<BiboxTicker>,
        @SerializedName("cmd") val cmd: String
) {
    data class BiboxTicker(
            @SerializedName("id") val id: Int,
            @SerializedName("is_hide") val isHide: Int,
            @SerializedName("coin_symbol") val coinSymbol: String,
            @SerializedName("currency_symbol") val currencySymbol: String,
            @SerializedName("last") val last: Double,
            @SerializedName("high") val high: Double,
            @SerializedName("low") val low: Double,
            @SerializedName("change") val change: Double,
            @SerializedName("percent") val percent: String,
            @SerializedName("vol24H") val vol24H: String,
            @SerializedName("amount") val amount: Double,
            @SerializedName("last_cny") val lastCny: String,
            @SerializedName("high_cny") val highCny: String,
            @SerializedName("low_cny") val lowCny: String,
            @SerializedName("last_usd") val lastUsd: String,
            @SerializedName("high_usd") val highUsd: String,
            @SerializedName("low_usd") val lowUsd: String
    ) : ITicker {
        override fun toTicker() = Ticker(
                currency = coinSymbol,
                baseCurrency = currencySymbol,
                last = last,
                high = high,
                low = low,
                diff = change / (last - change) * 100,
                volume = amount
        )

        override fun toExchangeTicker(exchange: String) = ExchangeTicker("BiBox", toTicker())
    }
}