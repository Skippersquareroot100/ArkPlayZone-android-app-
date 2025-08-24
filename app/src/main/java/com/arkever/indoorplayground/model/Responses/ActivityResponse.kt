package com.arkever.indoorplayground.model.Responses

import com.arkever.indoorplayground.model.DTOs.ActivityDto

data class ActivityResponse(
    val statusCode: Int,
    val message: String,
    val game: ActivityDto
)