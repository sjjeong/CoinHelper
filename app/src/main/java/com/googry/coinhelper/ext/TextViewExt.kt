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
    when ((ticker.baseCurrency ?: "").toUpperCase()) {
        BaseCurrency.KRW.name -> {
            var amount: Long = (ticker.volume)!!.toLong()
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
            var amount: Long = (ticker.volume)!!.toLong()
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
            var amount: Long = (ticker.volume)!!.toLong()
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
    text = if (ticker.last ?: .0 > 1) {
        intFormat.format(ticker.last ?: 0)
    } else {
        doubleFormat.format(ticker.last ?: 0)
    }
}

@BindingAdapter(value = ["tradeDiff"])
fun TextView.setTradeDiff(ticker: Ticker) {
    text = ticker.diff?.let {
        when {
            it > 0 -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.diff_up, null))
            }
            it < 0 -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.diff_down, null))
            }
            else -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.gray5, null))

            }
        }
        String.format("%.2f%%", it)
    } ?: ""

}

@BindingAdapter(value = ["priceDiffA", "priceDiffB"])
fun TextView.setPriceDiff(priceDiffA: Double, priceDiffB: Double) {
    val priceDiff = priceDiffA - priceDiffB
    text = priceDiff.let {
        when {
            it > 0 -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.diff_up, null))
            }
            it < 0 -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.diff_down, null))
            }
            else -> {
                setTextColor(ResourcesCompat.getColor(resources, R.color.gray5, null))

            }
        }
        if ((it == Math.floor(it)) && !it.isInfinite()) {
            intFormat.format(it)
        } else {
            doubleFormat.format(it)
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