package com.googry.coinhelper.ext

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this)
                .load(it)
                .into(this)
    }
}