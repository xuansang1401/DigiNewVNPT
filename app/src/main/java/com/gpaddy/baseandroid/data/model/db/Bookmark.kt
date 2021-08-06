package com.gpaddy.baseandroid.data.model.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gpaddy.baseandroid.data.model.api.Item

@Entity
data class Bookmark(
    @PrimaryKey val id_book: String,
    @Embedded val news : Item,
    val time:Long,
)

