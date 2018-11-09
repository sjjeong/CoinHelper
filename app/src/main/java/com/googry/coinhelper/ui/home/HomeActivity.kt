package com.googry.coinhelper.ui.home

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.widget.DrawerLayout
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.databinding.library.baseAdapters.BR
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.googry.coinhelper.BuildConfig
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.base.ui.BaseRecyclerViewAdapter
import com.googry.coinhelper.base.ui.BaseViewHolder
import com.googry.coinhelper.databinding.ExchangeSelectItemBinding
import com.googry.coinhelper.databinding.HomeActivityBinding
import com.googry.coinhelper.ext.replaceFragmentInActivity
import com.googry.coinhelper.ui.menu.MenuFragment
import com.googry.coinhelper.viewmodel.CoinSortViewModel
import com.googry.coinhelper.viewmodel.ExchangeSelectViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import org.koin.android.architecture.ext.viewModel
import org.koin.android.architecture.ext.viewModelByClass

class HomeActivity
    : BaseActivity<HomeActivityBinding>(R.layout.home_activity) {

    private val exchangeSelectViewModel by viewModel<ExchangeSelectViewModel>()

    private val coinSortViewModel by viewModelByClass(true, CoinSortViewModel::class)

    private var exitTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            view = this@HomeActivity
            coinSortVM = coinSortViewModel
            exchangeSelectVM = exchangeSelectViewModel
            replaceFragmentInActivity(MenuFragment.newInstance(), R.id.fl_end_side)
            dlRoot.run {
                setScrimColor(Color.TRANSPARENT)
                addDrawerListener(object : DrawerLayout.DrawerListener {
                    override fun onDrawerStateChanged(newState: Int) {
                    }

                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                        if (drawerView.id == R.id.fl_end_side) {
                            clRoot.translationX = -clRoot.width * slideOffset
                        }
                    }

                    override fun onDrawerClosed(drawerView: View) {
                    }

                    override fun onDrawerOpened(drawerView: View) {
                    }
                })
            }
            tlContent.setupWithViewPager(vpContent)
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
                    icArrowForward.rotation = slideOffset * 180
                }

                override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {

                }
            })
            rvExchangeList.adapter = object : BaseRecyclerViewAdapter<String, ExchangeSelectItemBinding>(
                    layoutRes = R.layout.exchange_select_item,
                    bindingVariableId = BR.exchange
            ) {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ExchangeSelectItemBinding> {
                    return super.onCreateViewHolder(parent, viewType).apply {
                        itemView.setOnClickListener {
                            if (exchangeSelectViewModel.selectedItemPosition != adapterPosition) {
                                val prevPosition = exchangeSelectViewModel.selectedItemPosition
                                exchangeSelectViewModel.selectedItemPosition = adapterPosition
                                exchangeSelectViewModel.saveMainExchange()
                                notifyItemChanged(prevPosition)
                                notifyItemChanged(adapterPosition)
                                refreshPage()
                            }
                        }
                    }
                }

                override fun onBindViewHolder(holder: BaseViewHolder<ExchangeSelectItemBinding>, position: Int) {
                    super.onBindViewHolder(holder, position)
                    holder.binding.run {
                        selectedPosition = exchangeSelectViewModel.selectedItemPosition
                        itemPosition = holder.adapterPosition
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
        if (binding.dlRoot.isDrawerOpen(binding.flEndSide)) {
            binding.dlRoot.closeDrawer(binding.flEndSide)
            return
        }
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(applicationContext, R.string.description_back_finish, Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }


    fun refreshPage() {
        binding.run {
            suplRoot.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            tvExchange.text = getString(exchangeSelectViewModel.getSelectedExchange().nameRes)
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

    fun onMenuClick() {
        binding.dlRoot.openDrawer(binding.flEndSide)
    }
}
