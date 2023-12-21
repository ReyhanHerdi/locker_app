package com.example.locker.data.api

import com.example.locker.data.model.RequestData
import com.example.locker.data.response.TestResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("predict")
    suspend fun scan(
        @Body requestData: RequestData
    ): TestResponse

}