package com.arkever.indoorplayground.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkever.indoorplayground.model.DTOs.StaffDto
import com.arkever.indoorplayground.repository.StaffRepository


class StaffViewModel : ViewModel() {
    private val repository = StaffRepository()
    private val _registrationResult = MutableLiveData<Pair<Boolean,String>>()
    val registrationResult : LiveData<Pair<Boolean,String>> = _registrationResult

    fun registerStaff(staff : StaffDto)
    {
        repository.createStaff(staff) { success, message ->
            _registrationResult.postValue(Pair(success, message))

        }

    }



}