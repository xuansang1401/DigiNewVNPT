package com.gpaddy.baseandroid.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger
import com.gpaddy.baseandroid.data.model.api.NewsModel
import com.gpaddy.baseandroid.network.NetworkRequest
import com.gpaddy.baseandroid.util.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class NetworkViewModel @ViewModelInject constructor(
    private val request: NetworkRequest
) : ViewModel() {

    var data= MutableLiveData<NewsModel>()
    fun getRXBus(): Disposable? {
        return RxBus.events(NewsModel::class.java)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Logger.d("Sang: $it")
                data.value = it
            }
    }

    val isLoading = ObservableBoolean(true)
    val liveData = MutableLiveData<List<NewsModel>>()
    fun getNews(): Disposable? {
        return request.getAllNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.value = it
                isLoading.set(false)
            }, {
                isLoading.set(false)
                Log.e("sang", "Recommend that bai $it")
            })
    }


}