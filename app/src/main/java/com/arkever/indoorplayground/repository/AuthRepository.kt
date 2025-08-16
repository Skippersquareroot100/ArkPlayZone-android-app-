package com.arkever.indoorplayground.repository

import com.arkever.indoorplayground.model.DTOs.LoginDto
import com.arkever.indoorplayground.model.Responses.LoginResponse
import com.arkever.indoorplayground.networks.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {
    fun login(email:String, password:String , onResult:(Boolean,String)->Unit)
    {
        val loginDto = LoginDto(email,password)
        RetrofitClient.instance.loginStaff(loginDto).enqueue(object :Callback<LoginResponse>
        {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>)
            {
               if(response.isSuccessful && response.body()!=null)
               {
                   onResult(true, response.body()!!.token)
               }
                else
               {
                   onResult(false, "Login Failed: ${response.code()}")
               }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onResult(false, t.localizedMessage?:"Unknown Erorr")
            }
        })



    }

}