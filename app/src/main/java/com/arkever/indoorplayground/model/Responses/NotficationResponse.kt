package com.arkever.indoorplayground.model.Responses

import com.arkever.indoorplayground.model.PusherNotification

data class NotficationResponse(
    val id: Int,
    val pusherNotification: PusherNotification,
    val isRead: Boolean,
    val createdAt: String
)
