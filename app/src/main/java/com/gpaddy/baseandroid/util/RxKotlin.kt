package com.gpaddy.baseandroid.util

import io.reactivex.Observable

class RxKotlin {
    fun <T> creteRx(t: T): Observable<T> {
        return Observable.create{
            it.onNext(t)

            it.onComplete()
        }
    }
}