package com.gpaddy.baseandroid.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gpaddy.baseandroid.data.model.api.NewsModel
import com.gpaddy.baseandroid.data.room.DatabaseDao
import com.gpaddy.baseandroid.network.NetworkRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel @ViewModelInject constructor(
   private val networkRequest: NetworkRequest,
): ViewModel() {
    val isLoadItem=ObservableBoolean(false)
    val newsData= MutableLiveData<NewsModel>()
    fun getNewsHome(): Disposable {
      val url ="https://vnexpress.net/rss/tin-moi-nhat.rss"
        return networkRequest.getNewsByUrl(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                newsData.value = it
                isLoadItem.set(true)
            }, {
                Log.e("sang", "getItemSale False: $it")
                isLoadItem.set(true)
            })
    }

    fun getNewsHot(): Disposable {
        val url ="https://vnexpress.net/rss/tin-noi-bat.rss"
        return networkRequest.getNewsByUrl(url)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                newsData.value = it
                isLoadItem.set(true)
            }, {
                Log.e("sang", "getItemSale False: $it")
                isLoadItem.set(true)
            })
    }
}