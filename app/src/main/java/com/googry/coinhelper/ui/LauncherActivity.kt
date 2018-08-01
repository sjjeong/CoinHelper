package com.googry.coinhelper.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.googry.coinhelper.BuildConfig
import com.googry.coinhelper.R
import com.googry.coinhelper.data.source.MainExchangeDataSource
import com.googry.coinhelper.ui.home.HomeActivity
import com.googry.coinhelper.ui.welcome.WelcomeActivity
import org.koin.android.ext.android.inject

class LauncherActivity : AppCompatActivity() {

    val mainExchangeDataSource by inject<MainExchangeDataSource>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)

        if (BuildConfig.DEBUG) {
            startApp()
        } else {
            MobileAds.initialize(applicationContext, getString(R.string.admob_app_key))
            with(InterstitialAd(applicationContext)) {
                adUnitId = getString(R.string.admob_start_interstitial)
                loadAd(AdRequest.Builder().build())
                adListener = object : AdListener() {

                    override fun onAdFailedToLoad(p0: Int) {
                        startApp()
                        super.onAdFailedToLoad(p0)
                    }

                    override fun onAdClosed() {
                        super.onAdClosed()
                        startApp()
                    }

                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        show()
                    }

                }
            }
        }
    }

    fun startApp() {
        mainExchangeDataSource.loadMainExchange {
            if (it == null) {
                startActivity(Intent(applicationContext, WelcomeActivity::class.java))
            } else {
                startActivity(Intent(applicationContext, HomeActivity::class.java))
            }
            finish()
        }
    }
}
