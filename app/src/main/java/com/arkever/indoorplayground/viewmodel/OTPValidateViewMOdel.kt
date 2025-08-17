package com.arkever.indoorplayground.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkever.indoorplayground.repository.OTPValidateRepository

class OTPValidateViewMOdel: ViewModel(){

    private val repository = OTPValidateRepository()

    private val _otpValidateResult = MutableLiveData<Pair<Boolean, String>>()
    val otpValidateResult : LiveData<Pair<Boolean, String>> = _otpValidateResult

    fun vrifyOTP(email: String, otp : String)
    {
        repository.verifyOTP(email,otp)
        {
            success, message ->
            _otpValidateResult.postValue(Pair(success,message))
        }
    }


}