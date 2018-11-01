package com.googry.coinhelper.network.model

import com.google.gson.annotations.SerializedName

data class BiboxTickerResponse(
        @SerializedName("error") val error: String? = null,
        @SerializedName("result") val result: BiboxAllTickerResponse.BiboxTicker?,
        @SerializedName("cmd") val cmd: String
)