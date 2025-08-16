package com.arkever.indoorplayground.model.Responses

data class ApiResponse
    (
    val statusCode: Int,
    val message: String,
    val error: String
)