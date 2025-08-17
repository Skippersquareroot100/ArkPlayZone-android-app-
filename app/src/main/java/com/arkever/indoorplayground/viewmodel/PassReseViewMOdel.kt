package com.arkever.indoorplayground.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkever.indoorplayground.repository.PassResetRepository
import com.arkever.indoorplayground.view.ForgetPassword

class PassReseViewMOdel: ViewModel() {

    private val repository = PassResetRepository()
    private val _passresetResult = MutableLiveData<Pair<Boolean, String>>()
    val passresetResult :  LiveData<Pair<Boolean, String>> = _passresetResult

    fun updatePassword(email: String, password: String)
    {
        repository.updatePassword(email,password)
        {
            succes, message ->
            _passresetResult.postValue(Pair(succes,message))

        }
    }

}