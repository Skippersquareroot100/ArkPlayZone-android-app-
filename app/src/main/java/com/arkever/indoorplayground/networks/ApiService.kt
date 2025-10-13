package com.arkever.indoorplayground.networks

import android.R
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
import com.arkever.indoorplayground.model.Responses.NotficationResponse
import com.arkever.indoorplayground.model.Responses.StaffResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import retrofit2.http.Multipart

interface ApiService {
    @POST("create-staff/")
    fun createStaff(@Body data: StaffDto): Call<ApiResponse>
    @POST("hello/")
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

    @GET("staff")
    fun getStaffNotificationsByEmail(@Query("email") email: String): Call<List<NotficationResponse>>

    @GET("staff/{id}")
    fun getStaffById(@retrofit2.http.Path("id") id: Int): Call<StaffResponse>

    @Multipart
    @PUT("{id}")
    fun updateStaff(
        @retrofit2.http.Path("id") id: Int,
        @retrofit2.http.Part("fname") fname: okhttp3.RequestBody,
        @retrofit2.http.Part("mname") mname: okhttp3.RequestBody,
        @retrofit2.http.Part("lname") lname: okhttp3.RequestBody,
        @retrofit2.http.Part("email") email: okhttp3.RequestBody,
        @retrofit2.http.Part("phone") phone: okhttp3.RequestBody,
        @retrofit2.http.Part("city") city: okhttp3.RequestBody,
        @retrofit2.http.Part("postalcode") postalcode: okhttp3.RequestBody,
        @retrofit2.http.Part("street") street: okhttp3.RequestBody,
        @retrofit2.http.Part("sname") sname: okhttp3.RequestBody,
        @retrofit2.http.Part("apartment") apartment: okhttp3.RequestBody,
        @retrofit2.http.Part("password") password: okhttp3.RequestBody,
        @retrofit2.http.Part file: okhttp3.MultipartBody.Part?
    ): Call<ApiResponse>


}
