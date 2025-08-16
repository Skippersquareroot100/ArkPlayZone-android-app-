package com.arkever.indoorplayground.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkever.indoorplayground.repository.AuthRepository

class AuthViewModel: ViewModel() {
    private val  repository = AuthRepository()
    private  val  _loginResult = MutableLiveData<Pair<Boolean,String>>()
    val loginResult : LiveData<Pair<Boolean,String>> = _loginResult

    fun login(email:String, password:String)
    {

        repository.login(email,password)
        {
            success, tokenOrMessage ->
            _loginResult.postValue(Pair(success,tokenOrMessage))
        }

    }

}