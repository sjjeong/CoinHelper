package com.googry.coinhelper.ext

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.googry.coinhelper.base.ui.BaseRecyclerViewAdapter

@BindingAdapter("bind:replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) =
        (this.adapter as? BaseRecyclerViewAdapter<Any>)?.run {
            replaceAll(list)
            notifyDataSetChanged()
        }
