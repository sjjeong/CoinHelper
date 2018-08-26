package com.googry.coinhelper.ext

import android.databinding.BindingAdapter
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.widget.TextViewCompat
import android.widget.TextView
import com.googry.coinhelper.R
import com.googry.coinhelper.data.enums.BaseCurrency
import com.googry.coinhelper.data.model.Ticker
import java.text.DecimalFormat

@BindingAdapter(value = ["tradeAmount"])
fun TextView.setTradeAmount(ticker: Ticker) {
    when (ticker.baseCurrency.toUpperCase()) {
        BaseCurrency.KRW.name -> {
            var amount: Long = (ticker.volume).toLong()
            text = String.format(context.getString(
                    when {
                        amount < 1_000_000L -> {
                            R.string.trade_amount_fmt
                        }
                        amount < 1_000_000_000_000L -> {
                            amount /= 1_000_000L
                            R.string.trade_amount_mega_fmt
                        }
                        else -> {
                            amount /= 1_000_000_000L
                            R.string.trade_amount_giga_fmt
                        }
                    }
            ), amount)
        }
        BaseCurrency.BTC.name,
        BaseCurrency.ETH.name -> {
            text = String.format(context.getString(R.string.trade_amount_milli_fmt, ticker.volume))
        }
        BaseCurrency.USDT.name -> {
            var amount: Long = (ticker.volume).toLong()
            text = String.format(context.getString(when {
                amount < 1_000_000L -> {
                    R.string.trade_amount_fmt
                }
                amount < 1_000_000_000L -> {
                    amount /= 1_000L
                    R.string.trade_amount_kilo_fmt
                }
                amount < 1_000_000_000_000L -> {
                    amount /= 1_000_000L
                    R.string.trade_amount_mega_fmt
                }
                else -> {
                    amount /= 1_000_000_000L
                    R.string.trade_amount_giga_fmt
                }
            }), amount)

        }
        else -> {
            var amount: Long = (ticker.volume).toLong()
            var fmtResId = when {
                amount < 1_000L -> {
                    R.string.trade_amount_milli_fmt
                }
                amount < 1_000_000L -> {
                    R.string.trade_amount_fmt
                }
                amount < 1_000_000_000L -> {
                    amount /= 1_000L
                    R.string.trade_amount_kilo_fmt
                }
                amount < 1_000_000_000_000L -> {
                    amount /= 1_000_000L
                    R.string.trade_amount_mega_fmt
                }
                else -> {
                    amount /= 1_000_000_000L
                    R.string.trade_amount_giga_fmt
                }
            }
            text = if (amount < 1_000L) {
                String.format(context.getString(fmtResId), ticker.volume)
            } else {
                String.format(context.getString(fmtResId), amount)
            }
        }
    }
}

val doubleFormat = DecimalFormat("#,##0.00000000")
val intFormat = DecimalFormat("#,###.##")
@BindingAdapter(value = ["last"])
fun TextView.setLast(ticker: Ticker) {
    text = if (ticker.last > 1) {
        intFormat.format(ticker.last)
    } else {
        doubleFormat.format(ticker.last)
    }
}

@BindingAdapter(value = ["tradeDiff"])
fun TextView.setTradeDiff(ticker: Ticker) {
    val diff = (ticker.last - ticker.first) / ticker.first * 100
    text = String.format("%.2f%%", diff)
    when {
        diff > 0 -> {
            setTextColor(ResourcesCompat.getColor(resources, R.color.diff_up, null))
        }
        diff < 0 -> {
            setTextColor(ResourcesCompat.getColor(resources, R.color.diff_down, null))
        }
        else -> {
            setTextColor(ResourcesCompat.getColor(resources, R.color.gray5, null))

        }
    }
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