package com.arkever.indoorplayground.model.Responses

data class ForgetPassResponse(
    val statusCode: Int,
    val message: String,
    val error: String
)