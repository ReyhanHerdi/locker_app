package com.example.locker.data.response

import com.google.gson.annotations.SerializedName

data class RequestData (
    @SerializedName("text_list")
    val text_list: String
)