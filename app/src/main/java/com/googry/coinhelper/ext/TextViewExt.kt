package com.googry.coinhelper.ext

import android.databinding.BindingAdapter
import android.support.v4.widget.TextViewCompat
import android.widget.TextView
import com.googry.coinhelper.R
import java.text.DecimalFormat

@BindingAdapter(value = ["tradeAmount"])
fun TextView.setTradeAmount(tradeAmount: Double) {
    var amount: Long = (tradeAmount).toLong()
    var fmtResId = if (amount < 1_000_000_000_000L) {
        amount /= 1_000_000L
        R.string.trade_amount_m_fmt
    } else {
        amount /= 1_000_000_000L
        R.string.trade_amount_g_fmt
    }
    text = String.format(context.getString(fmtResId), amount)
}

val doubleFormat = DecimalFormat("#,###.##")
@BindingAdapter(value = ["last"])
fun TextView.setLast(last: Double) {
    text = doubleFormat.format(last)
}


@BindingAdapter(value = ["autoSizeText"])
fun TextView.setAutoSizeText(autoSizeText: Boolean) {
    TextViewCompat.setAutoSizeTextTypeWithDefaults(this,
            if (autoSizeText) {
                TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
            } else {
                TextViewCompat.AUTO_SIZE_TEXT_TYPE_NONE
            }
    )
}