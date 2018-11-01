package com.googry.coinhelper.ui.welcome

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseFragment
import com.googry.coinhelper.base.ui.BaseRecyclerViewAdapter
import com.googry.coinhelper.base.ui.BaseViewHolder
import com.googry.coinhelper.databinding.ExchangeSelectItemBinding
import com.googry.coinhelper.databinding.MainExchangeSelectFragmentBinding
import com.googry.coinhelper.viewmodel.ExchangeSelectViewModel
import org.koin.android.architecture.ext.viewModel

class MainExchangeSelectFragment
    : BaseFragment<MainExchangeSelectFragmentBinding>(R.layout.main_exchange_select_fragment) {

    private val exchangeSelectViewModel by viewModel<ExchangeSelectViewModel>()

    companion object {
        fun newInstance() = MainExchangeSelectFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            setLifecycleOwner(this@MainExchangeSelectFragment)
            vm = exchangeSelectViewModel
            rvContent.adapter = object : BaseRecyclerViewAdapter<String>() {

                var selectedItemView: View? = null

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                        object : BaseViewHolder<String, ExchangeSelectItemBinding>(
                                R.layout.exchange_select_item,
                                parent
                        ) {

                            init {
                                itemView.setOnClickListener {
                                    exchangeSelectViewModel.selectedItemPosition = adapterPosition
                                    exchangeSelectViewModel.saveMainExchange()
                                    notifyDataSetChanged()
                                }
                            }

                            override fun onViewCreated(item: String?) {
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

    fun saveMainExchange(): Boolean {
        return exchangeSelectViewModel.saveMainExchange()
    }
}