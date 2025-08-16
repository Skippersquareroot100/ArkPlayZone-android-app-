package com.arkever.indoorplayground.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkever.indoorplayground.repository.RefreshRepository

class RefreshViewModel: ViewModel()
{
    private val repository = RefreshRepository()
    private val _refreshResult = MutableLiveData<Pair<Boolean,String>>()
    val refreshResult : LiveData<Pair<Boolean, String>>  = _refreshResult

    fun refresh(email: String)
    {
        repository.refresh(email)
        {
             success, tokenOrMessage ->
            _refreshResult.postValue(Pair(success,tokenOrMessage))
        }

    }

}