package com.googry.coinhelper.ui.welcome.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.googry.coinhelper.ui.welcome.MainExchangeSelectFragment
import com.googry.coinhelper.ui.welcome.SnsLoginFragment
import com.googry.coinhelper.ui.welcome.WelcomeFragment

class WelcomeAdapter(fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {

    private val fragments = mutableListOf<Fragment>().apply {
        add(WelcomeFragment.newInstance())
//        add(SnsLoginFragment.newInstance())
        add(MainExchangeSelectFragment.newInstance())
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}