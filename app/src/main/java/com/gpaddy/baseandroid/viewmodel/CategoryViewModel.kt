package com.gpaddy.baseandroid.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gpaddy.baseandroid.data.model.CategoryModel
import com.gpaddy.baseandroid.data.model.api.NewsModel
import com.gpaddy.baseandroid.network.NetworkRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CategoryViewModel @ViewModelInject constructor(
    private val networkRequest: NetworkRequest
) : ViewModel() {
    //    Thế giới RSS
//    Thời sự RSS
//    Kinh doanh RSS
//    Startup RSS
//    Giải trí RSS
//    Thể thao RSS
//    Pháp luật RSS
//    Giáo dục RSS
//    Tin mới nhấtRSS
//    Tin nổi bậtRSS
//    Sức khỏeRSS
//    Đời sốngRSS
//    Du lịchRSS

    val isLoadItem= ObservableBoolean(false)
    val newsData= MutableLiveData<NewsModel>()
    fun getListCategory(): MutableList<CategoryModel> {
        val list = mutableListOf<CategoryModel>()
        list.add(CategoryModel(0, "Thế giới", "https://vnexpress.net/rss/the-gioi.rss"))
        list.add(CategoryModel(1, "Thời sự", "https://vnexpress.net/rss/thoi-su.rss"))
        list.add(CategoryModel(2, "Kinh doanh", "https://vnexpress.net/rss/kinh-doanh.rss"))
        list.add(CategoryModel(3, "Startup", "https://vnexpress.net/rss/startup.rss"))
        list.add(CategoryModel(4, "Giải trí", "https://vnexpress.net/rss/giai-tri.rss"))
        list.add(CategoryModel(5, "Thể thao", "https://vnexpress.net/rss/the-thao.rss"))
        list.add(CategoryModel(6, "Pháp luật", "https://vnexpress.net/rss/phap-luat.rss"))
        list.add(CategoryModel(7, "Giáo dục", "https://vnexpress.net/rss/giao-duc.rss"))

        return list
    }

    fun getNewsCate(url: String): Disposable {
        isLoadItem.set(false)
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