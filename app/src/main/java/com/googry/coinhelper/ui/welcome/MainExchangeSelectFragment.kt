package com.googry.coinhelper.ui.welcome

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
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
            rvContent.adapter = object : BaseRecyclerViewAdapter<String, ExchangeSelectItemBinding>(
                    layoutRes = R.layout.exchange_select_item,
                    bindingVariableId = BR.exchange
            ) {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ExchangeSelectItemBinding> {
                    return super.onCreateViewHolder(parent, viewType).apply {
                        itemView.setOnClickListener {
                            val prevPosition = exchangeSelectViewModel.selectedItemPosition
                            exchangeSelectViewModel.selectedItemPosition = adapterPosition
                            exchangeSelectViewModel.saveMainExchange()
                            notifyItemChanged(prevPosition)
                            notifyItemChanged(adapterPosition)
                        }
                    }
                }

                override fun onBindViewHolder(holder: BaseViewHolder<ExchangeSelectItemBinding>, position: Int) {
                    super.onBindViewHolder(holder, position)
                    holder.binding.run {
                        selectedPosition = exchangeSelectViewModel.selectedItemPosition
                        itemPosition = position
                    }
                }
            }
        }
    }

    fun saveMainExchange(): Boolean {
        return exchangeSelectViewModel.saveMainExchange()
    }
}