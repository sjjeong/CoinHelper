package com.googry.coinhelper.ui.coin

import android.os.Bundle
import com.android.databinding.library.baseAdapters.BR
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.base.ui.BaseRecyclerViewAdapter
import com.googry.coinhelper.base.ui.BaseViewHolder
import com.googry.coinhelper.data.model.ExchangeTicker
import com.googry.coinhelper.databinding.CoinCompareActivityBinding
import com.googry.coinhelper.databinding.CoinCompareItemBinding
import com.googry.coinhelper.viewmodel.CoinCompareViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.architecture.ext.viewModel

class CoinCompareActivity
    : BaseActivity<CoinCompareActivityBinding>(R.layout.coin_compare_activity) {

    companion object {
        const val EXTRA_CURRENCY = "EXTRA_CURRENCY"
        const val EXTRA_BASE_CURRENCY = "EXTRA_BASE_CURRENCY"
    }

    private val coinCompareViewModel by viewModel<CoinCompareViewModel>()

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coinCompareViewModel.liveCurrency.value = intent.getStringExtra(EXTRA_CURRENCY).toUpperCase()
        coinCompareViewModel.liveBaseCurrency.value = intent.getStringExtra(EXTRA_BASE_CURRENCY).toUpperCase()
        binding.run {
            coinCompareVM = coinCompareViewModel
            rvContent.adapter = object : BaseRecyclerViewAdapter<ExchangeTicker, CoinCompareItemBinding>(
                    layoutRes = R.layout.coin_compare_item,
                    bindingVariableId = BR.exchangeTicker
            ) {
                override fun onBindViewHolder(holder: BaseViewHolder<CoinCompareItemBinding>, position: Int) {
                    super.onBindViewHolder(holder, position)
                    holder.binding.targetExchangeTicker = coinCompareViewModel.liveTargetExchangeTicker.value
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable = coinCompareViewModel.getExchangeTickers()
    }

    override fun onStop() {
        compositeDisposable?.clear()
        super.onStop()
    }

}