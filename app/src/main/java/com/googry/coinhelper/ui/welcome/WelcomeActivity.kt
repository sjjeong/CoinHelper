package com.googry.coinhelper.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.databinding.WelcomeActivityBinding
import com.googry.coinhelper.ui.home.HomeActivity
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
            this.welcomeViewModel = this@WelcomeActivity.welcomeViewModel.apply {
                nextActivity = {
                    (vpContent.adapter as? WelcomeAdapter)?.run {
                        liveCurrentPagePosition.value?.let {
                            if ((getItem(it) as MainExchangeSelectFragment).saveMainExchange()) {
                                startActivity(Intent(applicationContext, HomeActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(applicationContext, getString(R.string.please_select_main_exchange), Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
            vpContent.adapter = WelcomeAdapter(supportFragmentManager).also {
                welcomeViewModel?.pageCnt = it.count
            }
        }
    }
}