package com.googry.coinhelper.data.enums

import com.googry.coinhelper.R

enum class Exchange(val nameRes: Int, val baseCurrencies: List<String>) {
    COINONE(R.string.coinone, arrayListOf("KRW")),
    UPBIT(R.string.upbit, arrayListOf("KRW", "BTC", "ETH", "USDT")),
    BITHUMB(R.string.bithumb, arrayListOf("KRW"))
}