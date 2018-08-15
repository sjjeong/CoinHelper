package com.googry.coinhelper.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseFragment
import com.googry.coinhelper.base.ui.BaseRecyclerViewAdapter
import com.googry.coinhelper.base.ui.BaseViewHolder
import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.databinding.ExchangeListFragmentBinding
import com.googry.coinhelper.databinding.ExchangeSelectItemBinding
import com.googry.coinhelper.viewmodel.ExchangeSelectViewModel
import org.koin.android.architecture.ext.viewModel

class ExchangeListFragment
    : BaseFragment<ExchangeListFragmentBinding>(R.layout.exchange_list_fragment) {


    private val exchangeSelectViewModel by viewModel<ExchangeSelectViewModel>()

    companion object {
        fun newInstance() = ExchangeListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            exchangeSelectVM = exchangeSelectViewModel
            rvContent.adapter = object : BaseRecyclerViewAdapter<Exchange>() {

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                        object : BaseViewHolder<Exchange, ExchangeSelectItemBinding>(
                                R.layout.exchange_select_item,
                                parent
                        ) {

                            init {
                                itemView.setOnClickListener {
                                    if (exchangeSelectViewModel.selectedItemPosition != adapterPosition) {
                                        exchangeSelectViewModel.selectedItemPosition = adapterPosition
                                        exchangeSelectViewModel.saveMainExchange()
                                        notifyDataSetChanged()
                                        (activity as? HomeActivity)?.refreshPage()
                                    }
                                }
                            }

                            override fun onViewCreated(item: Exchange?) {
                                binding.run {
                                    exchange = item
                                    selectedPosition = exchangeSelectViewModel.selectedItemPosition
                                    itemPosition = adapterPosition
                                }
                            }
                        }
            }
        }
    }
}