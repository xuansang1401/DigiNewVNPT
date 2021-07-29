package com.gpaddy.baseandroid.viewmodel

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.pixplicity.easyprefs.library.Prefs
import com.gpaddy.baseandroid.data.model.db.HistoryModel
import com.gpaddy.baseandroid.data.room.DatabaseDao

class LiveRoomViewModel @ViewModelInject constructor(
    private val dao: DatabaseDao
): ViewModel() {
    val id = ObservableField<String>()
    val title = ObservableField<String>(Prefs.getString("SANG","NGU"))
    fun addData(historyModel: HistoryModel) {
        dao.addHis(historyModel)
    }
    fun getAll()=  dao.getAllHistory()
    fun getDaoDatabase()=dao
}