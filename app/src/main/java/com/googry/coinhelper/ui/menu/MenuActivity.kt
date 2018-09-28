package com.googry.coinhelper.ui.menu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.databinding.MenuActivityBinding
import com.googry.coinhelper.ui.WebViewActivity
import org.jetbrains.anko.intentFor

class MenuActivity
    : BaseActivity<MenuActivityBinding>(R.layout.menu_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            view = this@MenuActivity
        }
    }

    fun onShowWebView(url: String, title: String) {
        startActivity(intentFor<WebViewActivity>(WebViewActivity.EXTRA_WEB_URL to url,
                WebViewActivity.EXTRA_TITLE to title))
    }

    fun onShowKakaoPlus(){
        val uri = "kakaoplus://plusfriend/friend/@coinhelper#Intent;scheme=kakaoplus;package=com.kakao.talk;end"
        val intent = Intent.parseUri(uri, Intent.URI_INTENT_SCHEME)
        startActivity(intent)
    }

    fun onShowKakaoOpenTalkClick(){
        val uri = "kakaoopen://join?l=g1Zqy0Z&r=EW"
        val intent = Intent.parseUri(uri, Intent.URI_INTENT_SCHEME)
        startActivity(intent)
    }

    fun onOpenMarketClick(){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
    }
}