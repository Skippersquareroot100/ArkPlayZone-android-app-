package com.arkever.indoorplayground.repository

import android.util.Log
import com.arkever.indoorplayground.model.DTOs.PassResetDTO
import com.arkever.indoorplayground.model.Responses.PassResetResponse
import com.arkever.indoorplayground.networks.RetrofitClient
import com.arkever.indoorplayground.utils.SharedPrefEmail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassResetRepository {

    fun updatePassword(email: String, password: String, onResult:(Boolean, String)-> Unit)
    {
        val passResetDto = PassResetDTO(email,password)
        RetrofitClient.instance.updatePassword(passResetDto).enqueue(object : Callback<PassResetResponse>
        {
            override fun onResponse(
                call: Call<PassResetResponse?>,
                response: Response<PassResetResponse?>
            ) {
                val body = response.body()
                if(body!=null)
                {
                    Log.d("status-code", body.statusCode.toString())
                    Log.d("status-message", body.message)
                    Log.d("status-error", body.error ?: "No error")
                    if (body.statusCode == 200) {
                        val msg = if (body.message.isNullOrBlank()) "Password updated successfully" else body.message
                        onResult(true, msg)
                    } else {
                        onResult(false, body.message)
                    }

                }
                else
                {
                    onResult(false,"Empty Response From Server")
                }
            }

            override fun onFailure(call: Call<PassResetResponse?>, t: Throwable) {
                onResult(false, t.localizedMessage?:"Unknown ERROR!")
            }
        })

    }
}