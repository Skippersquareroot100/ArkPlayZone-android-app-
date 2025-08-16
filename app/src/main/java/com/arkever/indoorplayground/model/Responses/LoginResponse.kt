package com.arkever.indoorplayground.model.Responses

data class LoginResponse(
        val statusCode: Int,
        val message: String,
        val error: String,
        val token : String
        )