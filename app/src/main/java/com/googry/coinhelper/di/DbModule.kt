package com.googry.coinhelper.di

import com.googry.coinhelper.util.PrefUtils
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

val dbModule = applicationContext {
    bean { PrefUtils(androidApplication()) }
}