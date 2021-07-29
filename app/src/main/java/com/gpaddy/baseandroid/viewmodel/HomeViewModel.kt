package com.gpaddy.baseandroid.viewmodel

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gpaddy.baseandroid.data.model.api.Demo
import com.gpaddy.baseandroid.data.room.DatabaseDao

class HomeViewModel @ViewModelInject constructor(
        val dao: DatabaseDao
): ViewModel() {
    val data= MutableLiveData<Demo>()
    val id = ObservableField<String>()
    val ho = ObservableField<String>()
    val ten = ObservableField<String>()

    fun dd(){
        
    }
}