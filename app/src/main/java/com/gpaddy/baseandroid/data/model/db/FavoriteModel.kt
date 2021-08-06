package com.gpaddy.baseandroid.data.model.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gpaddy.baseandroid.data.model.api.Item

@Entity
data class FavoriteModel(
    @PrimaryKey val id_fav: String,
    @Embedded val news : Item,
    val time:Long,
)