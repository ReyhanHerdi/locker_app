package com.example.locker.data.api

import com.example.locker.data.response.RequestData
import com.example.locker.data.response.TestResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("predict")
    @Headers("Accept: application/jsonContent")
    suspend fun scan(
        @Body requestData: RequestData
    ): TestResponse

}