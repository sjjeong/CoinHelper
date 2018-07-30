package com.googry.coinhelper.ui.home

import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.util.Log
import android.view.View
import android.widget.Toast
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseActivity
import com.googry.coinhelper.databinding.HomeActivityBinding
import com.googry.coinhelper.ext.addFragment
import com.googry.coinhelper.ui.home.coinlist.CoinListFragment
import com.googry.coinhelper.viewmodel.MainExchangeSelectViewModel
import org.koin.android.architecture.ext.viewModel

class HomeActivity
    : BaseActivity<HomeActivityBinding>(R.layout.home_activity) {

    private val coinListFragment by lazy { CoinListFragment.newInstance() }

    private val exitToast by lazy { Toast.makeText(applicationContext, R.string.description_back_finish, Toast.LENGTH_LONG) }

    private val mainExchangeSelectViewModel by viewModel<MainExchangeSelectViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(coinListFragment)
        binding.run {
            view = this@HomeActivity
            dlRoot.run {
                setScrimColor(Color.TRANSPARENT)
                addDrawerListener(object : DrawerLayout.DrawerListener {
                    override fun onDrawerStateChanged(newState: Int) {
                    }

                    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                        if (drawerView.id == R.id.fl_side_right) {
                            clRoot.translationX = -clRoot.width * slideOffset
                            flSideRight.scaleX = 1 - slideOffset / 5
                            flSideRight.scaleY = 1 - slideOffset / 5
                        }
                    }

                    override fun onDrawerClosed(drawerView: View) {
                    }

                    override fun onDrawerOpened(drawerView: View) {
                    }
                })
            }
        }
        mainExchangeSelectViewModel.getMainExchange {
            Log.e("googry", getString(it.nameRes))
        }
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
}
