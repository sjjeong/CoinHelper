package com.googry.coinhelper.ui

import android.os.Bundle
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.databinding.WebViewActivityBinding

class WebViewActivity
    : BaseActivity<WebViewActivityBinding>(R.layout.web_view_activity) {

    companion object {
        const val EXTRA_WEB_URL = "EXTRA_WEB_URL"
        const val EXTRA_TITLE = "EXTRA_TITLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            webView.loadUrl(intent.getStringExtra(EXTRA_WEB_URL))
            tvTitle.text = intent.getStringExtra(EXTRA_TITLE)
        }
    }
}