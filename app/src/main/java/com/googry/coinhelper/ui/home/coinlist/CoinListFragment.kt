package com.googry.coinhelper.ui.home.coinlist

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseFragment
import com.googry.coinhelper.base.ui.BaseRecyclerViewAdapter
import com.googry.coinhelper.base.ui.BaseViewHolder
import com.googry.coinhelper.data.model.ITicker
import com.googry.coinhelper.data.model.Ticker
import com.googry.coinhelper.databinding.CoinListFragmentBinding
import com.googry.coinhelper.databinding.CoinListItemBinding
import com.googry.coinhelper.viewmodel.CoinListViewModel
import org.koin.android.architecture.ext.viewModel


class CoinListFragment
    : BaseFragment<CoinListFragmentBinding>(R.layout.coin_list_fragment) {

    val coinListViewModel by viewModel<CoinListViewModel>()

    companion object {
        fun newInstance() = CoinListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            setLifecycleOwner(this@CoinListFragment)
            this.coinListViewModel = this@CoinListFragment.coinListViewModel
            rvContent.run {
                adapter = object : BaseRecyclerViewAdapter<Ticker>() {
                    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                            object : BaseViewHolder<Ticker, CoinListItemBinding>(
                                    R.layout.coin_list_item, parent
                            ) {
                                override fun onViewCreated(item: Ticker?) {
                                    binding?.run {
                                        ticker = item
                                    }
                                }
                            }
                }
            }
        }
        compositeDisposable.add(coinListViewModel.getAllTickers())

    }
}