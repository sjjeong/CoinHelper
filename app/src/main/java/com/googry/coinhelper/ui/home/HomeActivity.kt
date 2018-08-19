package com.googry.coinhelper.ui.home

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.widget.DrawerLayout
import android.view.View
import android.widget.Toast
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.databinding.HomeActivityBinding
import com.googry.coinhelper.ext.logE
import com.googry.coinhelper.ext.replaceFragmentInActivity
import com.googry.coinhelper.viewmodel.ExchangeSelectViewModel
import org.koin.android.architecture.ext.viewModel

class HomeActivity
    : BaseActivity<HomeActivityBinding>(R.layout.home_activity) {

    private val exchangeListFragment by lazy { ExchangeListFragment.newInstance() }

    private val exitToast by lazy { Toast.makeText(applicationContext, R.string.description_back_finish, Toast.LENGTH_LONG) }

    private val exchangeSelectViewModel by viewModel<ExchangeSelectViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragmentInActivity(exchangeListFragment, R.id.fl_side_right)

        binding.run {
            view = this@HomeActivity
            dlRoot.run {
                setScrimColor(Color.TRANSPARENT)
                addDrawerListener(object : DrawerLayout.DrawerListener {
                    override fun onDrawerStateChanged(newState: Int) {
                        logE("onDrawerStateChanged $newState")
                    }

                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                        if (drawerView.id == R.id.fl_side_right) {
                            clRoot.translationX = -clRoot.width * slideOffset
                            flSideRight.scaleX = 1 - slideOffset / 5
                            flSideRight.scaleY = 1 - slideOffset / 5

                            icArrowForward.rotation = slideOffset * 180
                        }
                    }

                    override fun onDrawerClosed(drawerView: View) {
                        logE("onDrawerClosed")
                    }

                    override fun onDrawerOpened(drawerView: View) {
                        logE("onDrawerOpened")
                    }
                })
            }
            tlContent.setupWithViewPager(vpContent)
        }

        refreshPage()
    }

    override fun onBackPressed() {
        if (binding.dlRoot.isDrawerOpen(binding.flSideRight)) {
            binding.dlRoot.closeDrawer(binding.flSideRight)
            return
        }
        if (exitToast.view.windowVisibility == View.VISIBLE) {
            super.onBackPressed()
        } else {
            exitToast.show()
        }
    }

    fun onOpenSideMenuClick() {
        binding.dlRoot.openDrawer(binding.flSideRight)
    }

    fun refreshPage() {
        binding.run {
            tvExchange.text = getString(R.string.for_exchange_fmt, getString(exchangeSelectViewModel.getSelectedExchange().nameRes))
            vpContent.adapter = object : FragmentPagerAdapter(supportFragmentManager) {

                val pageTitles = exchangeSelectViewModel.getBaseCurrencies()

                override fun getItem(position: Int) = CoinListFragment.newInstance(pageTitles[position])

                override fun getCount() = pageTitles.size

                override fun getPageTitle(position: Int) = pageTitles[position]
            }
        }
    }

}
