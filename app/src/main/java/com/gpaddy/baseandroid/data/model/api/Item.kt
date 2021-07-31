package com.gpaddy.baseandroid.data.model.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val author: String,
    val content: String,
    val description: String,
    val guid: String,
    val link: String,
    val pubDate: String,
    val thumbnail: String?,
    val title: String
): Parcelable