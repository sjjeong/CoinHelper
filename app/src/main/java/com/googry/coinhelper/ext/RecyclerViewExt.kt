package com.googry.coinhelper.ext

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.googry.coinhelper.base.ui.BaseRecyclerViewAdapter

@BindingAdapter(value = ["replaceAll"])
fun RecyclerView.replaceAll(list: List<Any>?) {
    @Suppress("UNCHECKED_CAST")
    (this.adapter as? BaseRecyclerViewAdapter<Any, *>)?.let {
        it.replaceAll(list)
        it.notifyDataSetChanged()
    }
}
