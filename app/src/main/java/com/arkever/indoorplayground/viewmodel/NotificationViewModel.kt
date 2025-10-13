package com.arkever.indoorplayground.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkever.indoorplayground.model.NotificationItem
import com.arkever.indoorplayground.repository.NotificationRepository

class NotificationViewModel : ViewModel(){
    private  val repository = NotificationRepository()

    private val _notifications = MutableLiveData<List<NotificationItem>>()

    val notification: LiveData<List<NotificationItem>> = _notifications

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadNotifications(email: String) {
        repository.getStaffNotificationsByEmail(email) { success, data, error ->
            if (success && data != null) {
                _notifications.postValue(data)
            } else {
                _error.postValue(error ?: "Unknown error occurred")
            }
        }
    }

}