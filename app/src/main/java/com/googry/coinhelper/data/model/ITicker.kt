package com.googry.coinhelper.data.model

interface ITicker {
    fun toTicker(): Ticker

    fun toExchangeTicker(exchange: String = "empty"): ExchangeTicker
}