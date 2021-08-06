package com.gpaddy.baseandroid.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gpaddy.baseandroid.data.model.api.Item
import com.gpaddy.baseandroid.data.model.api.NewsModel
import com.gpaddy.baseandroid.data.model.db.Bookmark
import com.gpaddy.baseandroid.data.model.db.DownloadModel
import com.gpaddy.baseandroid.data.model.db.FavoriteModel
import com.gpaddy.baseandroid.data.model.db.HistoryModel
import com.gpaddy.baseandroid.data.room.DatabaseDao
import com.gpaddy.baseandroid.network.NetworkRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.sql.ClientInfoStatus
import java.util.*

class HomeViewModel @ViewModelInject constructor(
   private val networkRequest: NetworkRequest,
   private val databaseDao: DatabaseDao
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

    fun addBookmark(item : Item){
        databaseDao.addBookmark(Bookmark(item.link,item,Date().time))
    }
    fun addDownload(item : Item){
        databaseDao.addDownloadModel(DownloadModel(item.link,item,Date().time))
    }
    fun addFav(item : Item){
        databaseDao.addFavoriteModel(FavoriteModel(item.link,item,Date().time))
    }
    fun addHis(item : Item){
        databaseDao.addHis(HistoryModel(item.link,item,Date().time))
    }

    fun getHistory()= databaseDao.getHistory()
    fun getFav()= databaseDao.getFavoriteModel()
    fun getDown()= databaseDao.getDownloadModel()
    fun getBook()= databaseDao.getBookmark()

    fun getListAll(status: Int): LiveData<List<Item>> {
        return when(status){
            1-> getAllHistory()
            2-> getAllFav()
            3-> getAllDown()
            else -> getAllBook()
        }
    }
    fun getAllHistory()= databaseDao.getAllHistory()
    fun getAllFav()= databaseDao.getAllFavoriteModel()
    fun getAllDown()= databaseDao.getAllDownloadModel()
    fun getAllBook()= databaseDao.getAllBookmark()
    fun delete(status: Int) {
        return when(status){
            1-> databaseDao.deleteAllHis()
            2-> databaseDao.deleteAllFav()
            3-> databaseDao.deleteAllDow()
            else -> databaseDao.deleteAllBook()
        }

    }

}