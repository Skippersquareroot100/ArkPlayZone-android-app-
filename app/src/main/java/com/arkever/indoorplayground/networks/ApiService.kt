package com.arkever.indoorplayground.networks

import com.arkever.indoorplayground.model.DTOs.ForgetPassDto
import com.arkever.indoorplayground.model.DTOs.LoginDto
import com.arkever.indoorplayground.model.DTOs.OTPValidateDto
import com.arkever.indoorplayground.model.DTOs.PassResetDTO
import com.arkever.indoorplayground.model.DTOs.RefreshDto
import com.arkever.indoorplayground.model.Responses.LoginResponse
import com.arkever.indoorplayground.model.DTOs.StaffDto
import com.arkever.indoorplayground.model.Responses.ApiResponse
import com.arkever.indoorplayground.model.Responses.ForgetPassResponse
import com.arkever.indoorplayground.model.ActivityResponse
import com.arkever.indoorplayground.model.Responses.OTPValidateResponse
import com.arkever.indoorplayground.model.Responses.PassResetResponse
import com.arkever.indoorplayground.model.Responses.RefreshResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
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
    @POST("OTP-verify")
    fun verifyOTP(@Body data: OTPValidateDto): Call<OTPValidateResponse>
    @PATCH("update-pass")
    fun updatePassword(@Body data: PassResetDTO): Call<PassResetResponse>
    @GET("activity")
    fun getAllActivities(): Call<ActivityResponse>

}
