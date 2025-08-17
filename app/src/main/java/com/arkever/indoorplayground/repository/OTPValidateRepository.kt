package com.arkever.indoorplayground.repository

import com.arkever.indoorplayground.model.DTOs.OTPValidateDto
import com.arkever.indoorplayground.model.Responses.OTPValidateResponse
import com.arkever.indoorplayground.networks.RetrofitClient
import retrofit2.Callback
import retrofit2.Call
import kotlin.math.log

class OTPValidateRepository {

    fun verifyOTP(email: String, otp: String, onResult:(Boolean, String)-> Unit)
    {
        val otpValidateDto = OTPValidateDto(email,otp)

        RetrofitClient.instance.verifyOTP(otpValidateDto).enqueue(object : Callback<OTPValidateResponse>
        {
            override fun onResponse(
                call: Call<OTPValidateResponse?>,
                response: retrofit2.Response<OTPValidateResponse?>
            ) {
                val body= response.body()
                if(body!= null)
                {

                    if(body.statusCode==200)
                    {
                        onResult(true,body.message)
                    }
                    else
                    {
                        onResult(false,body.message)
                    }
                }
                else{
                    onResult(false,"Empty Response From Server")
                }
            }

            override fun onFailure(call: Call<OTPValidateResponse?>, t: Throwable) {
                onResult(false, t.localizedMessage?:"Unknown ERROR!")
            }
        })

    }

}