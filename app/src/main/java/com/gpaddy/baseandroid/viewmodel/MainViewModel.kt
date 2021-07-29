package com.gpaddy.baseandroid.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.gpaddy.baseandroid.data.model.db.HistoryModel
import com.gpaddy.baseandroid.data.room.DatabaseDao

class MainViewModel @ViewModelInject constructor(
    private val dao: DatabaseDao
): ViewModel(){
    fun addData(historyModel: HistoryModel) {
        dao.addHis(historyModel)
    }
    fun getAll()=  dao.getAllHistory()
}