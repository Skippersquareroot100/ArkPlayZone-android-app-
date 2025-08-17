package com.arkever.indoorplayground.model.Responses

data class OTPValidateResponse(
    val statusCode: Int,
    val message: String,
    val error: String
)