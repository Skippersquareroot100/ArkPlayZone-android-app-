package com.arkever.indoorplayground.model.Responses

data class PassResetResponse(
    val statusCode: Int,
    val message: String,
    val error: String
)