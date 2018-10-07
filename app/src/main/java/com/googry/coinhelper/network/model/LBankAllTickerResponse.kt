package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class LBankAllTickerResponse(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("ticker") val ticker: LBankTicker,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("result") val result: String? = null
) {
    data class LBankTicker(
        @SerializedName("change") val change: Double,
        @SerializedName("high") val high: Double,
        @SerializedName("latest") val latest: Double,
        @SerializedName("low") val low: Double,
        @SerializedName("turnover") val turnover: Double,
        @SerializedName("vol") val vol: Double
    ) : ITicker{
        override fun toTicker() = Ticker(
                last = latest,
                high = high,
                low = low,
                diff = change,
                volume = vol * latest
        )

        override fun toExchangeTicker(exchange: String) = ExchangeTicker("LBank", toTicker())
    }
}
//{
//    "symbol": "eth_btc",
//    "ticker": {
//    "change": -0.3580127358629115,
//    "high": 0.034368,
//    "latest": 0.033955,
//    "low": 0.03379,
//    "turnover": 2246.2051744841556,
//    "vol": 65871.29239999999
//},
//    "timestamp": 1538929615090
//}