package com.googry.coinhelper.ui.home

import android.os.Bundle
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import android.widget.Toast
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.databinding.HomeActivityBinding
import com.googry.coinhelper.ext.replaceFragmentInActivity
import com.googry.coinhelper.viewmodel.CoinSortViewModel
import com.googry.coinhelper.viewmodel.ExchangeSelectViewModel
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import org.koin.android.architecture.ext.viewModel
import org.koin.android.architecture.ext.viewModelByClass

class HomeActivity
    : BaseActivity<HomeActivityBinding>(R.layout.home_activity) {

    private val exchangeListFragment by lazy { ExchangeListFragment.newInstance() }

    private val exitToast by lazy { Toast.makeText(applicationContext, R.string.description_back_finish, Toast.LENGTH_LONG) }

    private val exchangeSelectViewModel by viewModel<ExchangeSelectViewModel>()

    private val coinSortViewModel by viewModelByClass(true, CoinSortViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragmentInActivity(exchangeListFragment, R.id.supl_content)

        binding.run {
            view = this@HomeActivity
            coinSortVM = coinSortViewModel
//            dlRoot.run {
//                setScrimColor(Color.TRANSPARENT)
//                addDrawerListener(object : DrawerLayout.DrawerListener {
//                    override fun onDrawerStateChanged(newState: Int) {
//                    }
//
//                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//                        if (drawerView.id == R.id.fl_side_right) {
//                            clRoot.translationX = -clRoot.width * slideOffset
//                            flSideRight.scaleX = 1 - slideOffset / 5
//                            flSideRight.scaleY = 1 - slideOffset / 5
//
//                            icArrowForward.rotation = slideOffset * 180
//                        }
//                    }
//
//                    override fun onDrawerClosed(drawerView: View) {
//                    }
//
//                    override fun onDrawerOpened(drawerView: View) {
//                    }
//                })
//            }
            tlContent.setupWithViewPager(vpContent)
            tvExchange.setOnClickListener {
                suplRoot.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
            }

            suplRoot.setFadeOnClickListener {
                suplRoot.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
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

        }

        refreshPage()
    }

    override fun onBackPressed() {
//        if (binding.dlRoot.isDrawerOpen(binding.flSideRight)) {
//            binding.dlRoot.closeDrawer(binding.flSideRight)
//            return
//        }
        if (exitToast.view.windowVisibility == View.VISIBLE) {
            super.onBackPressed()
        } else {
            exitToast.show()
        }
    }

//    fun onOpenSideMenuClick() {
//        binding.dlRoot.openDrawer(binding.flSideRight)
//    }

    fun refreshPage() {
        binding.run {
            suplRoot.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
            tvExchange.text = getString(R.string.for_exchange_fmt, getString(exchangeSelectViewModel.getSelectedExchange().nameRes))
            val pageTitles = exchangeSelectViewModel.getBaseCurrencies()
            vpContent.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {

                override fun getItem(position: Int) = CoinListFragment.newInstance(pageTitles[position])

                override fun getCount() = pageTitles.size

                override fun getPageTitle(position: Int) = pageTitles[position]
            }
        }
    }

}
