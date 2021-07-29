package com.gpaddy.baseandroid.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import com.gpaddy.baseandroid.data.model.api.NewsModel
import com.gpaddy.baseandroid.network.NetworkRequest
import com.gpaddy.baseandroid.util.RxBus
import io.reactivex.disposables.Disposable

class DetilViewModel @ViewModelInject constructor(
    private val request: NetworkRequest
) : ViewModel() {

    init {
        getRXBus()
    }
    var data: NewsModel? = null
    fun getRXBus(): Disposable? {
        return RxBus.events(NewsModel::class.java)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Logger.d("Sang: $it")
                data = it
            }
    }


}