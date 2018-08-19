package com.googry.coinhelper.ext

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.networkCommunication(): Single<T> =
        this.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
