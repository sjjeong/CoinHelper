package com.googry.coinhelper.data.enums

import com.googry.coinhelper.R

enum class Exchange(val nameRes: Int, val baseCurrencies: List<String>) {
    COINONE(R.string.coinone, arrayListOf(BaseCurrency.KRW.name)),
    UPBIT(R.string.upbit, arrayListOf(BaseCurrency.KRW.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name, BaseCurrency.USDT.name)),
    BITHUMB(R.string.bithumb, arrayListOf(BaseCurrency.KRW.name)),
    GOPAX(R.string.gopax, arrayListOf(BaseCurrency.KRW.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name)),
    BINANCE(R.string.binance, arrayListOf(BaseCurrency.BNB.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name, BaseCurrency.USDT.name)),
    BITFINEX(R.string.bitfinex, arrayListOf(BaseCurrency.USD.name, BaseCurrency.EUR.name, BaseCurrency.GBP.name, BaseCurrency.JPY.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name, BaseCurrency.EOS.name))
}