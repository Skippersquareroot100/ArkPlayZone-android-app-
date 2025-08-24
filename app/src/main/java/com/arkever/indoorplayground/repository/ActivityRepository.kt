package com.arkever.indoorplayground.repository

import com.arkever.indoorplayground.model.ActivityItem

import com.arkever.indoorplayground.model.ActivityResponse

import com.arkever.indoorplayground.networks.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityRepository {
    fun getActivities(onResult: (Boolean, List<ActivityItem>?, String?) -> Unit) {
        RetrofitClient.instance.getAllActivities().enqueue(object : Callback<ActivityResponse> {
            override fun onResponse(call: Call<ActivityResponse>, response: Response<ActivityResponse>) {
                if (response.isSuccessful) {
                    val activities = response.body()?.data ?: emptyList()
                    onResult(true, activities, null)
                } else {
                    onResult(false, null, "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ActivityResponse>, t: Throwable) {
                onResult(false, null, t.message)
            }
        })


    }
}
