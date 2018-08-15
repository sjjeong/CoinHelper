package com.googry.coinhelper.ext

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("isSelected")
internal fun View.isSelected(selected: Boolean) {
    isSelected = selected
}