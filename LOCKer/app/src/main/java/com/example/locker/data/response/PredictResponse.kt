package com.example.locker.data.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("text_list")
	val textList: String? = null
)
