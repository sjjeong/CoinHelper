package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName

data class HuobiTickerResponse(
    @SerializedName("status") val status: String,
    @SerializedName("ch") val ch: String,
    @SerializedName("ts") val ts: Long,
    @SerializedName("tick") val tick: HuobiAllTickerResponse.HuobiTicker
)