package com.example.locker.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    var jobText: String,
    var resultScan: String
): Parcelable
