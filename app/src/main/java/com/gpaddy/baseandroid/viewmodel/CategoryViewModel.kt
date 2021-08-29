package com.gpaddy.baseandroid.viewmodel

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gpaddy.baseandroid.R
import com.gpaddy.baseandroid.data.model.api.NewsModel
import com.gpaddy.baseandroid.network.NetworkRequest
import com.gpaddy.baseandroid.theu.DAO.DatabaseNews
import com.gpaddy.baseandroid.theu.model.cataModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class CategoryViewModel @ViewModelInject constructor(
    private val networkRequest: NetworkRequest,
    @ApplicationContext val context: Context
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
    fun getListCategory(): MutableList<cataModel> {
        //
//        arr.add(new cataModel("Xu hướng", R.drawable.danhmuc));
//        arr.add(new cataModel("Xã hội", R.drawable.xahoi));
//        arr.add(new cataModel("Sức Khỏe", R.drawable.sk));
//        arr.add(new cataModel("Văn hóa", R.drawable.danhmuc));
//        arr.add(new cataModel("Giải trí", R.drawable.xahoi));
//        arr.add(new cataModel("Giáo dục", R.drawable.danhmuc));
//        arr.add(new cataModel("Thể thao", R.drawable.danhmuc));
//        arr.add(new cataModel("Tâm sự", R.drawable.tamsu));
//        arr.add(new cataModel("Truyện đọc", R.drawable.danhmuc));
//        arr.add(new cataModel("Bảng tin", R.drawable.danhmuc));
//        arr.add(new cataModel("Công nghệ", R.drawable.congnghe));
//        arr.add(new cataModel("Đời sống", R.drawable.doisong));

        val t=DatabaseNews.getInstance(context).daoNews().getListDM()
        if (!t.isNullOrEmpty()) return t
        val list = mutableListOf<cataModel>()
        list.add(cataModel("Thế giới", R.drawable.danhmuc, "https://vnexpress.net/rss/the-gioi.rss"))
        list.add(cataModel( "Thời sự",R.drawable.danhmuc, "https://vnexpress.net/rss/thoi-su.rss"))
        list.add(cataModel("Kinh doanh",R.drawable.danhmuc,  "https://vnexpress.net/rss/kinh-doanh.rss"))
        list.add(cataModel( "Startup",R.drawable.danhmuc,  "https://vnexpress.net/rss/startup.rss"))
        list.add(cataModel("Giải trí", R.drawable.danhmuc, "https://vnexpress.net/rss/giai-tri.rss"))
        list.add(cataModel( "Thể thao", R.drawable.danhmuc, "https://vnexpress.net/rss/the-thao.rss"))
        list.add(cataModel( "Pháp luật",R.drawable.danhmuc,  "https://vnexpress.net/rss/phap-luat.rss"))
        list.add(cataModel( "Giáo dục",R.drawable.danhmuc,  "https://vnexpress.net/rss/giao-duc.rss"))

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