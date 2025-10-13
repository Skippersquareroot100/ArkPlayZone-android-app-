package com.arkever.indoorplayground.model

data class NotificationItem(
    val id: Int,
    val title: String,
    val message: String,
    val date: String,
    val isRead: Boolean,
    val createdAt: String
)
