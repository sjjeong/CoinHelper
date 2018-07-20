package com.googry.coinhelper.ui.welcome

import android.os.Bundle
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.databinding.WelcomeActivityBinding
import com.googry.coinhelper.ui.welcome.adapter.WelcomeAdapter
import com.googry.coinhelper.viewmodel.WelcomeViewModel
import org.koin.android.architecture.ext.viewModel

class WelcomeActivity
    : BaseActivity<WelcomeActivityBinding>(R.layout.welcome_activity) {

    private val welcomeViewModel by viewModel<WelcomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            setLifecycleOwner(this@WelcomeActivity)
            this.welcomeViewModel = this@WelcomeActivity.welcomeViewModel
            vpContent.adapter = WelcomeAdapter(supportFragmentManager).also {
                welcomeViewModel?.pageCnt = it.count
            }
        }
    }
}