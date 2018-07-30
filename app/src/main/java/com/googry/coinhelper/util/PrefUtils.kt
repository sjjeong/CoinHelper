package com.googry.coinhelper.util

import android.app.Application
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.ext.fromJson


class PrefUtils(application: Application) {

    companion object {
        const val PREF_KEY_EXCHANGE = "PREF_KEY_EXCHANGE"
    }

    val sharedPref = PreferenceManager.getDefaultSharedPreferences(application)

    val gson = Gson()

    fun getEditor() = sharedPref.edit()

    fun saveExchange(exchange: Exchange) {
        getEditor().putString(PREF_KEY_EXCHANGE, gson.toJson(exchange)).apply()

    }

    fun loadExchange(): Exchange {
        val jsonString = sharedPref.getString(PREF_KEY_EXCHANGE, null) ?: return Exchange.COINONE
        return gson.fromJson<Exchange>(jsonString)
    }

}