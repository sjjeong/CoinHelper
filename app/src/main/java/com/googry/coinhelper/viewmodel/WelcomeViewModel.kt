package com.googry.coinhelper.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.googry.coinhelper.R
import com.googry.coinhelper.base.ui.BaseViewModel

class WelcomeViewModel
    : BaseViewModel() {

    var liveCurrentPagePosition = MutableLiveData<Int>().apply {
        value = 0

    }
    var liveBtnNextTextRes = MutableLiveData<Int>().apply {
        value = R.string.next
    }

    var pageCnt = 0

    var nextActivity: (() -> Any)? = null

    fun onNextClick() {
        liveCurrentPagePosition.value?.run {
            if (pageCnt > this + 1) {
                when (this) {
                    0 -> liveBtnNextTextRes.value = R.string.skip
                    1 -> liveBtnNextTextRes.value = R.string.start
                }
                liveCurrentPagePosition.value = this + 1
            } else {
                nextActivity?.invoke()
            }
        }
    }
}