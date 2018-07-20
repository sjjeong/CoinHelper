package com.googry.coinhelper.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.googry.coinhelper.R
import com.googry.coinhelper.ui.welcome.WelcomeActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        startActivity(Intent(applicationContext, WelcomeActivity::class.java))
        finish()
    }
}
