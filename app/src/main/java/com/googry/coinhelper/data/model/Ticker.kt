package com.googry.coinhelper.data.model

open class Ticker(
        var currency: String = "",
        var baseCurrency: String = "",
        var last: Double,
        var high: Double,
        var low: Double,
//                  var first: Double? = null,
        var volume: Double,
//diff는 %값 ex) 1.5%는 1.5로
        var diff: Double) : ITicker {

    override fun toTicker() = this

    override fun toExchangeTicker(exchange: String) = ExchangeTicker(exchange, this)
}