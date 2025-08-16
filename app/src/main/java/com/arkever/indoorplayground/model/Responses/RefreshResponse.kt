package com.arkever.indoorplayground.model.Responses

data class RefreshResponse(
    val statusCode: Int,
    val message: String,
    val error: String,
    val token : String
)