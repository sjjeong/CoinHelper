package com.googry.coinhelper.data.source

import com.googry.coinhelper.data.enums.Exchange

interface MainExchangeDataSource {

    fun saveMainExchange(exchange: Exchange)

    fun getSelectedExchange(): Exchange?
}