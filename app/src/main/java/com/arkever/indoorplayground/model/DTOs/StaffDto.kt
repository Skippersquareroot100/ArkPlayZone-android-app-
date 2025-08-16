package com.arkever.indoorplayground.model.DTOs

data class StaffDto(
    val firstName: String,
    val middleName: String?,
    val lastName: String,
    val street_no: String,
    val street_name: String,
    val apartment_name: String,
    val city: String,
    val state: String,
    val postal_code: String,
    val deduction: Int,
    val overtime: Int,
    val role: String,
    val email: String,
    val phone: String,
    val salary: Int,
    val password: String,
    val file :String
)
