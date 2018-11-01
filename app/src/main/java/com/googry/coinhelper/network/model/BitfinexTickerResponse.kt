package com.googry.coinhelper.network.model

import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.data.model.ExchangeTicker
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

    override fun toExchangeTicker(exchange: String) = ExchangeTicker(Exchange.BITFINEX.exchangeName, toTicker())

    companion object {

        const val LAST_POS = 6
        const val DIFF_POS = 5
        const val HIGH_POS = 8
        const val LOW_POS = 9
        const val VOLUME_POS = 7

    }
}