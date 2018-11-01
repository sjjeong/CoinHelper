package com.googry.coinhelper.data.enums

import android.content.Context
import com.googry.coinhelper.R

enum class Exchange(val exchangeName: String, val nameRes: Int, val baseCurrencies: List<String>) {
    COINONE("Coinone", R.string.coinone, arrayListOf(BaseCurrency.KRW.name)),
    UPBIT("Upbit", R.string.upbit, arrayListOf(BaseCurrency.KRW.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name, BaseCurrency.USDT.name)),
    BITHUMB("Bithumb", R.string.bithumb, arrayListOf(BaseCurrency.KRW.name)),
    GOPAX("Gopax", R.string.gopax, arrayListOf(BaseCurrency.KRW.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name)),
    BINANCE("Binance", R.string.binance, arrayListOf(BaseCurrency.BNB.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name, BaseCurrency.USDT.name)),
    BITFINEX("Bitfinex", R.string.bitfinex, arrayListOf(BaseCurrency.USD.name, BaseCurrency.EUR.name, BaseCurrency.GBP.name, BaseCurrency.JPY.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name, BaseCurrency.EOS.name)),
    HUOBI("Huobi", R.string.huobi, arrayListOf(BaseCurrency.USDT.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name, BaseCurrency.HT.name)),
    COINEX("CoinEx", R.string.coinex, arrayListOf(BaseCurrency.BCH.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name, BaseCurrency.USDT.name)),
    HITBTC("HitBTC", R.string.hitbtc, arrayListOf(BaseCurrency.BTC.name, BaseCurrency.ETH.name, BaseCurrency.USD.name, BaseCurrency.EURS.name, BaseCurrency.DAI.name, BaseCurrency.TUSD.name)),
    ZBCOM("ZB.com", R.string.zbcom, arrayListOf(BaseCurrency.QC.name, BaseCurrency.ZB.name, BaseCurrency.USDT.name, BaseCurrency.BTC.name, BaseCurrency.PAX.name)),
    LBANK("LBank", R.string.lbank, arrayListOf(BaseCurrency.LBCN.name, BaseCurrency.USDT.name, BaseCurrency.BTC.name, BaseCurrency.ETH.name, BaseCurrency.QTUM.name, BaseCurrency.ELA.name, BaseCurrency.DAX.name)),
    BIBOX("Bibox", R.string.bibox, arrayListOf(BaseCurrency.BIX.name, BaseCurrency.ETH.name, BaseCurrency.BTC.name, BaseCurrency.USDT.name, BaseCurrency.DAI.name, BaseCurrency.GUSD.name))


}

fun getExchange(name: String, context: Context): Exchange? {
    for (value in Exchange.values()) {
        if (name == context.getString(value.nameRes)) {
            return value
        }
    }
    return null
}