package com.example.locker.data.response

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("prediction")
	val prediction: List<List<Any?>?>? = null
)
