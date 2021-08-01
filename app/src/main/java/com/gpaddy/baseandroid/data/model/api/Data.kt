package com.gpaddy.baseandroid.data.model.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val channel: String,
    val id: Int,
    val image: String,
    val play_url: String,
    val published_at: Int,
    val title: String,
    val video_id: String
): Parcelable