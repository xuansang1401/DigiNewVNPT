package com.gpaddy.baseandroid.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gpaddy.baseandroid.data.model.api.NewsModel
import com.gpaddy.baseandroid.data.model.api.VideoModel
import com.pixplicity.easyprefs.library.Prefs
import com.gpaddy.baseandroid.data.model.db.HistoryModel
import com.gpaddy.baseandroid.data.room.DatabaseDao
import com.gpaddy.baseandroid.network.VideoRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class VideoViewModel @ViewModelInject constructor(
    private val videoRequest: VideoRequest
): ViewModel() {
    val isLoadItem= ObservableBoolean(false)
    val videoData= MutableLiveData<VideoModel>()
    fun getAllVideo(): Disposable {
        return videoRequest.getVideo()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                videoData.value = it
                isLoadItem.set(true)
            }, {
                Log.e("sang", "getItemSale False: $it")
                isLoadItem.set(true)
            })
    }

}