package com.arkever.indoorplayground.viewmodel

import ForgetPassRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class forgetPassViewModel :ViewModel()
{

    private val repository = ForgetPassRepository()
    private val _forgetPassResult = MutableLiveData<Pair<Boolean,String>>()
    val forgetPassResult : LiveData<Pair<Boolean,String>> = _forgetPassResult

    fun forgetPassword(email : String)
    {
        repository.forgetPassword(email){success, message ->
            _forgetPassResult.postValue(Pair(success,message))
        }
    }


}