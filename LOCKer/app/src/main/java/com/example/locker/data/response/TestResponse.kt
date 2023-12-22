package com.example.locker.data.response

import com.google.gson.annotations.SerializedName

data class TestResponse(
	@field:SerializedName("prediction")
	val prediction: List<List<Double?>?>? = null
)
