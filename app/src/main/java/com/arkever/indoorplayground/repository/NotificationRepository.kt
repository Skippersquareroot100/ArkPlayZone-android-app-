package com.arkever.indoorplayground.repository

import com.arkever.indoorplayground.model.NotificationItem
import com.arkever.indoorplayground.model.Responses.NotficationResponse
import com.arkever.indoorplayground.networks.RetrofitClientNotification
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationRepository {

    fun getStaffNotificationsByEmail(
        email: String,
        onResult: (Boolean, List<NotificationItem>, String?) -> Unit
    ) {
        RetrofitClientNotification.instance.getStaffNotificationsByEmail(email)
            .enqueue(object : Callback<List<NotficationResponse>> {
                override fun onResponse(
                    call: Call<List<NotficationResponse>>,
                    response: Response<List<NotficationResponse>>
                ) {
                    if (response.isSuccessful) {
                        val bodyList = response.body() ?: emptyList()
                        val notifications = bodyList.map { resp ->
                            NotificationItem(
                                id = resp.id,
                                title = resp.pusherNotification.title,
                                message = resp.pusherNotification.message,
                                date = resp.pusherNotification.date,
                                isRead = resp.isRead,
                                createdAt = resp.createdAt
                            )
                        }
                        onResult(true, notifications, null)
                    } else {
                        onResult(false, emptyList(), "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<NotficationResponse>>, t: Throwable) {
                    onResult(false, emptyList(), t.message)
                }
            })
    }
}
