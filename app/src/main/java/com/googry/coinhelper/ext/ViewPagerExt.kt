package com.googry.coinhelper.ext

import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager

@BindingAdapter(value = ["currentPosition"])
fun ViewPager.currentPosition(position: Int) {
    currentItem = position
}