package com.googry.coinhelper.di

import com.googry.coinhelper.viewmodel.WelcomeViewModel
import org.koin.dsl.module.applicationContext

val viewModelModule = applicationContext {
    bean { WelcomeViewModel() }
}