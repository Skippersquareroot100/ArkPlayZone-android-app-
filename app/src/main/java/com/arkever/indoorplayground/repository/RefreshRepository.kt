package com.arkever.indoorplayground.repository

import com.arkever.indoorplayground.model.DTOs.RefreshDto
import com.arkever.indoorplayground.model.Responses.RefreshResponse
import com.arkever.indoorplayground.networks.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RefreshRepository {

    fun refresh(email:String , onResult:(Boolean, String)->Unit)
    {
        val refreshdto = RefreshDto(email)

        RetrofitClient.instance.refresh(refreshdto).enqueue(object :Callback<RefreshResponse>
        {
            override fun onResponse(
                call: Call<RefreshResponse>,
                response: Response<RefreshResponse>
            ) {

                if(response.isSuccessful && response.body()!=null)
                {
                    onResult(true, response.body()!!.token)
                }
                else
                {
                    onResult(false, "Login Failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RefreshResponse>, t: Throwable) {
                onResult(false, t.localizedMessage?:"Unknown Erorr")
            }
        })


    }

}