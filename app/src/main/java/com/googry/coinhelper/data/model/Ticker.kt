package com.googry.coinhelper.data.model

data class Ticker(var currency: String,
                  var last: Double,
                  var high: Double,
                  var low: Double,
                  var first: Double,
                  var volume: Double)