package com.googry.coinhelper.base.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.googry.coinhelper.R
import com.googry.coinhelper.ext.addFragment

/**
 * Fragment를 보여주기 위한 Activity
 * Dagger를 사용하지 않음
 */
abstract class BaseFragmentBindingActivity : AppCompatActivity() {
    protected lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_fragment_binding_activity)
        fragment = createFragment().apply {
            addFragment(this)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    abstract fun createFragment(): Fragment

}