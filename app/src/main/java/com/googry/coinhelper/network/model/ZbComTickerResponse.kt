package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName

data class ZbComTickerResponse(
    @SerializedName("ticker") val ticker: ZbComAllTickerResponse.ZbComAllTicker?,
    @SerializedName("date") val date: String
)