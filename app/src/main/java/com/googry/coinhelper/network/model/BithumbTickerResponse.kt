package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName

data class BithumbTickerResponse(
        @SerializedName("status") val status: String,
        @SerializedName("data") val data: BithumbAllTickerResponse.BithumbTicker
)