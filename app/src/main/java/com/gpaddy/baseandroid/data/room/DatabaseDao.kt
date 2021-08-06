package com.gpaddy.baseandroid.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gpaddy.baseandroid.data.model.api.Item
import com.gpaddy.baseandroid.data.model.db.Bookmark
import com.gpaddy.baseandroid.data.model.db.DownloadModel
import com.gpaddy.baseandroid.data.model.db.FavoriteModel
import com.gpaddy.baseandroid.data.model.db.HistoryModel

@Dao
interface DatabaseDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun addHis(historyModel: HistoryModel)

    @Query("Select * from HistoryModel ORDER BY time DESC limit 20")
    fun getAllHistory(): LiveData<List<Item>>

    @Delete
    fun deleteHis(historyModel: HistoryModel)

    @Query("Select * from HistoryModel ORDER BY time DESC limit 5")
    fun getHistory(): LiveData<List<Item>>

    @Query("DELETE FROM HistoryModel")
    fun deleteAllHis()


    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun addBookmark(bookmark: Bookmark)

    @Query("Select * from Bookmark ORDER BY time DESC limit 20")
    fun getAllBookmark(): LiveData<List<Item>>

    @Delete
    fun deleteBookmark(bookmark: Bookmark)

    @Query("Select * from Bookmark ORDER BY time DESC limit 5")
    fun getBookmark(): LiveData<List<Item>>

    @Query("DELETE FROM Bookmark")
    fun deleteAllBook()


    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun addFavoriteModel(favoriteModel: FavoriteModel)

    @Query("Select * from FavoriteModel ORDER BY time DESC limit 20")
    fun getAllFavoriteModel(): LiveData<List<Item>>

    @Delete
    fun deleteFavoriteModel(favoriteModel: FavoriteModel)

    @Query("Select * from FavoriteModel ORDER BY time DESC limit 5")
    fun getFavoriteModel(): LiveData<List<Item>>

    @Query("DELETE FROM FavoriteModel")
    fun deleteAllFav()


    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun addDownloadModel(downloadModel: DownloadModel)

    @Query("Select * from DownloadModel ORDER BY time DESC limit 20")
    fun getAllDownloadModel(): LiveData<List<Item>>

    @Delete
    fun deleteDownloadModel(downloadModel: DownloadModel)

    @Query("Select * from DownloadModel ORDER BY time DESC limit 5")
    fun getDownloadModel(): LiveData<List<Item>>

    @Query("DELETE FROM DownloadModel")
    fun deleteAllDow()

}