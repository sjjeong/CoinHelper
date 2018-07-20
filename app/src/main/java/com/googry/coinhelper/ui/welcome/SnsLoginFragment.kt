package com.googry.coinhelper.ui.welcome

import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseFragment
import com.googry.coinhelper.databinding.SnsLoginFragmentBinding


class SnsLoginFragment
    : BaseFragment<SnsLoginFragmentBinding>(R.layout.sns_login_fragment) {


    companion object {
        fun newInstance() = SnsLoginFragment()
    }
}