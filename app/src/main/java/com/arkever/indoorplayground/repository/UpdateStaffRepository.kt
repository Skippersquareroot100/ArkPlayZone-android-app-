package com.arkever.indoorplayground.repository

import com.arkever.indoorplayground.model.Responses.ApiResponse
import com.arkever.indoorplayground.model.Responses.StaffResponse
import com.arkever.indoorplayground.networks.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UpdateStaffRepository {

    fun getStaffById(id: Int, callback: (Boolean, StaffResponse?, String?) -> Unit) {
        RetrofitClient.instance.getStaffById(id).enqueue(object : Callback<StaffResponse> {
            override fun onResponse(call: Call<StaffResponse>, response: Response<StaffResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    callback(true, response.body(), null)
                } else {
                    callback(false, null, "Failed to load staff info: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<StaffResponse>, t: Throwable) {
                callback(false, null, t.message)
            }
        })
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
        file: File?,
        callback: (Boolean, ApiResponse?, String?) -> Unit
    ) {
        val textPlain = "text/plain".toMediaTypeOrNull()
        fun partOf(value: String) = RequestBody.create(textPlain, value)

        val filePart = file?.let {
            val reqFile = RequestBody.create("image/*".toMediaTypeOrNull(), it)
            MultipartBody.Part.createFormData("file", it.name, reqFile)
        }

        RetrofitClient.instance.updateStaff(
            id,
            partOf(fname),
            partOf(mname),
            partOf(lname),
            partOf(email),
            partOf(phone),
            partOf(city),
            partOf(postalcode),
            partOf(street),
            partOf(sname),
            partOf(apartment),
            partOf(password),
            filePart
        ).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    callback(true, response.body(), null)
                } else {
                    val msg = response.errorBody()?.string() ?: "Update failed with code ${response.code()}"
                    callback(false, null, msg)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                callback(false, null, t.message)
            }
        })
    }
}
