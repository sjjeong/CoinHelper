package com.googry.coinhelper.data.model

data class Ticker(val currency: String,
                  val last: Double,
                  val high: Double,
                  val low: Double,
                  val first: Double,
                  val volume: Double)