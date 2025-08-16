package com.arkever.indoorplayground.networks

import com.arkever.indoorplayground.model.DTOs.ForgetPassDto
import com.arkever.indoorplayground.model.DTOs.LoginDto
import com.arkever.indoorplayground.model.DTOs.RefreshDto
import com.arkever.indoorplayground.model.Responses.LoginResponse
import com.arkever.indoorplayground.model.DTOs.StaffDto
import com.arkever.indoorplayground.model.Responses.ApiResponse
import com.arkever.indoorplayground.model.Responses.ForgetPassResponse
import com.arkever.indoorplayground.model.Responses.RefreshResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {


    @POST("create-staff")
    fun createStaff(@Body data: StaffDto): Call<ApiResponse>
    @POST("hello")
    fun hello(): Call<Void>
    @POST("staff-login")
    fun loginStaff(@Body data: LoginDto): Call<LoginResponse>
    @POST("refresh-token")
    fun refresh(@Body data:RefreshDto): Call<RefreshResponse>
    @POST("forget-pass")
    fun forgetPassword(@Body data:ForgetPassDto): Call<ForgetPassResponse>

}
