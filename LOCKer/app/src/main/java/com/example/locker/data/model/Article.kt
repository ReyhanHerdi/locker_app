package com.example.locker.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: String,
    val title: String,
    val content: String,
    val image: String,
    val author: String,
): Parcelable
