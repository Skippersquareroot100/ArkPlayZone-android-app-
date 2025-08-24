package com.arkever.indoorplayground.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkever.indoorplayground.model.ActivityItem
import com.arkever.indoorplayground.repository.ActivityRepository

class ActivityViewModel : ViewModel() {
    private val repository = ActivityRepository()

    private val _activities = MutableLiveData<List<ActivityItem>>()
    val activities: LiveData<List<ActivityItem>> = _activities

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadActivities() {
        repository.getActivities { success, data, err ->
            if (success && data != null) {
                _activities.postValue(data?: emptyList())
            } else {
                _error.postValue(err)
            }
        }
    }
}
