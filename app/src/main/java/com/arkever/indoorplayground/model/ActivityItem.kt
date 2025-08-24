package com.arkever.indoorplayground.model


data class ActivityResponse(
    val status: String,
    val data: List<ActivityItem>
)
data class ActivityItem(
    val name: String,
    val description: String,
    val photo: String
)


