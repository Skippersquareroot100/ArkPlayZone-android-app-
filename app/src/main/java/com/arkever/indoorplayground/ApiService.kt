package com.arkever.indoorplayground

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("create-staff")
    fun createStaff(@Body data: StaffDto): Call<Void>
    @POST("hello")
    fun hello(): Call<Void>
}
