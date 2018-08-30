package com.googry.coinhelper.network.model

import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker

data class BitfinexTickerResponse(val data: List<String>)
    : ITicker {
    override fun toTicker(): Ticker {
        val last = data[LAST_POS].toDouble()
        return Ticker(
                last = last,
                diff = data[DIFF_POS].toDouble() * 100,
                high = data[HIGH_POS].toDouble(),
                low = data[LOW_POS].toDouble(),
                volume = data[VOLUME_POS].toDouble() * last
        )
    }

    companion object {

        const val SYMBOL_POS = 0
        const val LAST_POS = 7
        const val DIFF_POS = 6
        const val HIGH_POS = 9
        const val LOW_POS = 10
        const val VOLUME_POS = 8

    }
}