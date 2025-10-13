package com.arkever.indoorplayground.model.Responses

data class StaffResponse(
    val id: Int,
    val fname: String?,
    val mname: String?,
    val lname: String?,
    val email: String?,
    val phone: String?,
    val city: String?,
    val postalcode: String?,
    val street: String?,
    val sname: String?,
    val apartment: String?,
    val imageUrl: String?
)