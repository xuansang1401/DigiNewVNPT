package com.gpaddy.baseandroid.data.model.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gpaddy.baseandroid.data.model.api.Item

@Entity
data class DownloadModel(
    @PrimaryKey val id_download: String,
    @Embedded val news : Item,
    val time:Long,
)