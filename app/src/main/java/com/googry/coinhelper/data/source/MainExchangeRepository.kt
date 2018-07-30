package com.googry.coinhelper.data.source

import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.util.PrefUtils

class MainExchangeRepository(private val prefUtils: PrefUtils)
    : MainExchangeDataSource {

    override fun saveMainExchange(exchange: Exchange) {
        prefUtils.saveExchange(exchange)
    }

    override fun loadMainExchange(response: (exchange: Exchange) -> Unit) {
        response.invoke(prefUtils.loadExchange())
    }
}