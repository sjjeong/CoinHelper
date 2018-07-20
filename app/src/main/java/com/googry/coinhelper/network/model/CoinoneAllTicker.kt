package com.googry.coinhelper.network.model


data class CoinoneAllTicker(
        val ltc: CoinoneTicker,
        val bch: CoinoneTicker,
        val zrx: CoinoneTicker,
        val qtum: CoinoneTicker,
        val knc: CoinoneTicker,
        val errorCode: String,
        val iota: CoinoneTicker,
        val eos: CoinoneTicker,
        val etc: CoinoneTicker,
        val btg: CoinoneTicker,
        val result: String,
        val btc: CoinoneTicker,
        val timestamp: String,
        val omg: CoinoneTicker,
        val eth: CoinoneTicker,
        val zil: CoinoneTicker,
        val data: CoinoneTicker,
        val xrp: CoinoneTicker
) {
    fun toMap() = HashMap<String, CoinoneTicker>().apply {
        put("ltc", ltc)
        put("bch", bch)
        put("zrx", zrx)
        put("qtum", qtum)
        put("knc", knc)
        put("iota", iota)
        put("eos", eos)
        put("etc", etc)
        put("btg", btg)
        put("btc", btc)
        put("omg", omg)
        put("eth", eth)
        put("zil", zil)
        put("items", data)
        put("xrp", xrp)
    }
}