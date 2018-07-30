package com.googry.coinhelper.ui.welcome

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseFragment
import com.googry.coinhelper.base.ui.BaseRecyclerViewAdapter
import com.googry.coinhelper.base.ui.BaseViewHolder
import com.googry.coinhelper.data.enums.Exchange
import com.googry.coinhelper.databinding.ExchangeSelectItemBinding
import com.googry.coinhelper.databinding.MainExchangeSelectFragmentBinding
import com.googry.coinhelper.viewmodel.MainExchangeSelectViewModel
import org.koin.android.architecture.ext.viewModel

class MainExchangeSelectFragment
    : BaseFragment<MainExchangeSelectFragmentBinding>(R.layout.main_exchange_select_fragment) {

    private val mainExchangeSelectViewModel by viewModel<MainExchangeSelectViewModel>()

    companion object {
        fun newInstance() = MainExchangeSelectFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            setLifecycleOwner(this@MainExchangeSelectFragment)
            vm = mainExchangeSelectViewModel
            rvContent.adapter = object : BaseRecyclerViewAdapter<Exchange>() {

                var selectedItemView: View? = null

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                        object : BaseViewHolder<Exchange, ExchangeSelectItemBinding>(
                                R.layout.exchange_select_item,
                                parent
                        ) {

                            init {
                                itemView.setOnClickListener {
                                    selectedItemView?.run {
                                        isSelected = false
                                    }
                                    selectedItemView = itemView
                                    selectedItemView?.isSelected = true
                                    vm?.selectedItem = adapterPosition
                                }
                            }

                            override fun onViewCreated(item: Exchange?) {
                                binding?.run {
                                    exchange = item
                                }
                            }
                        }
            }
        }
    }

    fun saveMainExchange(): Boolean{
        return mainExchangeSelectViewModel.saveMainExchange()
    }
}