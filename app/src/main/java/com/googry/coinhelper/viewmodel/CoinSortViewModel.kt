package com.googry.coinhelper.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.googry.coinhelper.base.ui.BaseViewModel
import io.reactivex.subjects.PublishSubject

class CoinSortViewModel
    : BaseViewModel() {

    var liveIsDescending = MutableLiveData<Boolean>().apply {
        value = (true)
    }

    var liveSelectedSortItem = MutableLiveData<String>().apply {
        value = ("volume")
    }

    var sortEventSubject = PublishSubject.create<Unit>()


    fun onSortClick(item: String) {
        if (item == liveSelectedSortItem.value) {
            liveIsDescending.value = (!liveIsDescending.value!!)
        } else {
            liveSelectedSortItem.value = (item)
        }
        sortEventSubject.onNext(Unit)
    }

}