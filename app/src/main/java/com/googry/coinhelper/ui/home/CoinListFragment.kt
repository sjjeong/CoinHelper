package com.googry.coinhelper.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseFragment
import com.googry.coinhelper.base.ui.BaseRecyclerViewAdapter
import com.googry.coinhelper.base.ui.BaseViewHolder
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.databinding.CoinListFragmentBinding
import com.googry.coinhelper.databinding.CoinListItemBinding
import com.googry.coinhelper.ui.coin.CoinCompareActivity
import com.googry.coinhelper.viewmodel.CoinListViewModel
import com.googry.coinhelper.viewmodel.CoinSortViewModel
import kotlinx.android.synthetic.main.coin_list_item.view.*
import org.jetbrains.anko.intentFor
import org.koin.android.architecture.ext.sharedViewModel
import org.koin.android.architecture.ext.viewModel


class CoinListFragment
    : BaseFragment<CoinListFragmentBinding>(R.layout.coin_list_fragment) {

    private val coinListViewModel by viewModel<CoinListViewModel>()

    private val coinSortViewModel by sharedViewModel<CoinSortViewModel>()

    companion object {

        const val KEY_BASE_CURRENCY = "KEY_BASE_CURRENCY"

        fun newInstance(baseCurrency: String) = CoinListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_BASE_CURRENCY, baseCurrency)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinListViewModel.baseCurrency = arguments?.getString(KEY_BASE_CURRENCY)
        coinListViewModel.coinSortViewModel = coinSortViewModel
        binding.run {
            coinListVM = coinListViewModel
            rvContent.run {
                adapter = object : BaseRecyclerViewAdapter<Ticker>() {
                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                            object : BaseViewHolder<Ticker, CoinListItemBinding>(
                                    R.layout.coin_list_item, parent
                            ) {
                                init {
                                    itemView.setOnClickListener {
                                        startActivity(context.intentFor<CoinCompareActivity>(CoinCompareActivity.EXTRA_CURRENCY to binding.ticker?.currency,
                                                CoinCompareActivity.EXTRA_BASE_CURRENCY to binding.ticker?.baseCurrency))
                                    }
                                }

                                override fun onViewCreated(item: Ticker?) {
                                    binding.run {
                                        ticker = item
                                    }
                                }
                            }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(coinListViewModel.getAllTickers())
    }

    override fun onPause() {
        compositeDisposable.clear()
        coinListViewModel.finish()
        super.onPause()
    }

}