package com.googry.coinhelper.data.model

class ExchangeTicker(val exchange: String,
                     ticker: Ticker)
    : Ticker(currency = ticker.currency,
        baseCurrency = ticker.baseCurrency,
        last = ticker.last,
        high = ticker.high,
        low = ticker.low,
        volume = ticker.volume,
        diff = ticker.diff)