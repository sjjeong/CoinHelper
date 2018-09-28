package com.googry.coinhelper.ui.coin

import android.os.Bundle
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.databinding.CoinCompareActivityBinding

class CoinCompareActivity
    : BaseActivity<CoinCompareActivityBinding>(R.layout.coin_compare_activity) {

    companion object {
        const val EXTRA_CURRENCY = "EXTRA_CURRENCY"
        const val EXTRA_BASE_CURRENCY= "EXTRA_BASE_CURRENCY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}