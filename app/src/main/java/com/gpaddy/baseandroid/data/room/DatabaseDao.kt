package com.gpaddy.baseandroid.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gpaddy.baseandroid.data.model.db.HistoryModel

@Dao
interface DatabaseDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    fun addHis(historyModel: HistoryModel)

    @Query("Select * from HistoryModel ORDER BY time DESC limit 20")
    fun getAllHistory(): LiveData<List<HistoryModel>>

    @Delete
    fun deleteHis(historyModel: HistoryModel)
}