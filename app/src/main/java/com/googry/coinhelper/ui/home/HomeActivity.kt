package com.googry.coinhelper.ui.home

import android.os.Bundle
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.googry.coinhelper.BuildConfig
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.base.ui.BaseRecyclerViewAdapter
import com.googry.coinhelper.base.ui.BaseViewHolder
import com.googry.coinhelper.databinding.ExchangeSelectItemBinding
import com.googry.coinhelper.databinding.HomeActivityBinding
import com.googry.coinhelper.ui.menu.MenuActivity
import com.googry.coinhelper.viewmodel.CoinSortViewModel
import com.googry.coinhelper.viewmodel.ExchangeSelectViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import org.koin.android.architecture.ext.viewModelByClass

class HomeActivity
    : BaseActivity<HomeActivityBinding>(R.layout.home_activity) {

    private val exitToast by lazy { Toast.makeText(applicationContext, R.string.description_back_finish, Toast.LENGTH_LONG) }

    private val exchangeSelectViewModel by viewModel<ExchangeSelectViewModel>()

    private val coinSortViewModel by viewModelByClass(true, CoinSortViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            view = this@HomeActivity
            coinSortVM = coinSortViewModel
            exchangeSelectVM = exchangeSelectViewModel
            icArrowForward.rotation = -90f
            tlContent.setupWithViewPager(vpContent)
            tvExchange.setOnClickListener {
                suplRoot.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }
            llTitle.setOnClickListener {
                suplRoot.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
            }
            vpContent.addOnAdapterChangeListener { viewPager, oldAdapter, _ ->
                (oldAdapter as? FragmentStatePagerAdapter)?.let {
                    val position = vpContent.currentItem
                    it.destroyItem(viewPager, position, it.getItem(position))
                    if (position > 0) {
                        it.destroyItem(viewPager, position - 1, it.getItem(position - 1))
                    }
                    val endPosition = it.count - 2
                    if (position < endPosition) {
                        it.destroyItem(viewPager, position + 1, it.getItem(position + 1))
                    }
                }
            }
            suplRoot.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
                override fun onPanelSlide(panel: View?, slideOffset: Float) {
                    icArrowForward.rotation = slideOffset * 180 - 90
                }

                override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {

                }
            })
            rvExchangeList.adapter = object : BaseRecyclerViewAdapter<String>() {

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                        object : BaseViewHolder<String, ExchangeSelectItemBinding>(
                                R.layout.exchange_select_item,
                                parent
                        ) {

                            init {
                                itemView.setOnClickListener {
                                    if (exchangeSelectViewModel.selectedItemPosition != adapterPosition) {
                                        exchangeSelectViewModel.selectedItemPosition = adapterPosition
                                        exchangeSelectViewModel.saveMainExchange()
                                        notifyDataSetChanged()
                                        refreshPage()
                                    }
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
            if (!BuildConfig.DEBUG) {
                adViewBanner.adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        adViewBanner.visibility = View.VISIBLE
                    }
                }
                val adRequest = AdRequest.Builder().build()
                adViewBanner.loadAd(adRequest)
            }
        }

        refreshPage()
    }

    override fun onBackPressed() {
        if (binding.suplRoot.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            binding.suplRoot.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            return
        }
        if (exitToast.view.windowVisibility == View.VISIBLE) {
            super.onBackPressed()
        } else {
            exitToast.show()
        }
    }


    fun refreshPage() {
        binding.run {
            suplRoot.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            tvExchange.text = getString(R.string.for_exchange_fmt, getString(exchangeSelectViewModel.getSelectedExchange().nameRes))
            val pageTitles = exchangeSelectViewModel.getBaseCurrencies()
            vpContent.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {

                override fun getItem(position: Int) = CoinListFragment.newInstance(pageTitles[position])

                override fun getCount() = pageTitles.size

                override fun getPageTitle(position: Int) = pageTitles[position]
            }
        }
    }

    fun onOpenExchangeListClick() {
        binding.suplRoot.panelState = if (binding.suplRoot.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            SlidingUpPanelLayout.PanelState.COLLAPSED
        } else {
            SlidingUpPanelLayout.PanelState.EXPANDED
        }
    }

    fun onMenuClick(){
        startActivity<MenuActivity>()
    }
}
