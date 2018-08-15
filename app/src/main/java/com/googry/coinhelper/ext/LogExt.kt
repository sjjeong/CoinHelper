package com.googry.coinhelper.ext

import android.util.Log
import com.googry.coinhelper.BuildConfig

internal fun logE(msg: String) {
    if (BuildConfig.DEBUG) {
        Log.e("googry", msg)
    }
}