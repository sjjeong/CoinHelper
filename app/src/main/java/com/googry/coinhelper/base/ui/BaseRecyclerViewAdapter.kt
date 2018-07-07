package com.googry.coinhelper.base.ui

import android.support.v7.widget.RecyclerView
import io.reactivex.subjects.PublishSubject

abstract class BaseRecyclerViewAdapter<ITEM : Any>
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var data = mutableListOf<ITEM>()

    val itemClickEvent: PublishSubject<Int> = PublishSubject.create()

    fun add(item: ITEM?) {
        item?.let {
            data.add(it)
        }
    }

    fun replaceAll(items: List<ITEM>?) {
        items?.let {
            data.run {
                clear()
                addAll(it)
            }
        }
    }

    fun clear() {
        data.clear()
    }

    fun getItem(position: Int) = data[position]


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BaseViewHolder<*, *>)?.onBindViewHolder(data[position])
    }
}