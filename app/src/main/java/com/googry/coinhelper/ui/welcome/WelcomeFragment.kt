package com.googry.coinhelper.ui.welcome

import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseFragment
import com.googry.coinhelper.databinding.WelcomeFragmentBinding

class WelcomeFragment
    : BaseFragment<WelcomeFragmentBinding>(R.layout.welcome_fragment) {


    companion object {
        fun newInstance() = WelcomeFragment()
    }
}