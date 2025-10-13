package com.arkever.indoorplayground.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkever.indoorplayground.model.Responses.ApiResponse
import com.arkever.indoorplayground.model.Responses.StaffResponse
import com.arkever.indoorplayground.repository.UpdateStaffRepository
import java.io.File

class UpdateStaffViewModel : ViewModel() {

    private val repo = UpdateStaffRepository()

    private val _staff = MutableLiveData<StaffResponse?>()
    val staff: LiveData<StaffResponse?> = _staff

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun loadStaff(id: Int) {
        _loading.postValue(true)
        repo.getStaffById(id) { success, data, err ->
            _loading.postValue(false)
            if (success) _staff.postValue(data) else _message.postValue(err)
        }
    }

    fun updateStaff(
        id: Int,
        fname: String,
        mname: String,
        lname: String,
        email: String,
        phone: String,
        city: String,
        postalcode: String,
        street: String,
        sname: String,
        apartment: String,
        password: String,
        file: File?
    ) {
        _loading.postValue(true)
        repo.updateStaff(id, fname, mname, lname, email, phone, city, postalcode, street, sname, apartment, password, file) { success, response, err ->
            _loading.postValue(false)
            if (success) {
                _message.postValue(response?.message ?: "Updated")
            } else {
                _message.postValue(err ?: "Update failed")
            }
        }
    }
}
