package com.arkever.indoorplayground.repository

import com.arkever.indoorplayground.model.DTOs.StaffDto
import com.arkever.indoorplayground.networks.ApiService
import com.arkever.indoorplayground.networks.RetrofitClient
import com.arkever.indoorplayground.model.Responses.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StaffRepository {
    fun createStaff(staff: StaffDto, onResult : (success:Boolean, massage:String )->Unit)
    {
        RetrofitClient.instance.createStaff(staff).enqueue(object : Callback<ApiResponse>

            {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                if(response.isSuccessful && response.body() != null)
                {
                    onResult(true, response.body()!!.message)
                }
                else
                {
                    val errormessage= "Registration failed : ${response.code()}"
                    onResult(false,errormessage)
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                onResult(false, t.localizedMessage ?: "Unknown error")
            }
        })
    }

}