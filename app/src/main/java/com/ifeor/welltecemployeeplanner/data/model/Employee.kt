package com.ifeor.welltecemployeeplanner.data.model

data class Employee (
    val employeeID: Int,
    val employeeFirstName: String,
    val employeeSecondName: String,
    val employeeEmail: String,
    val employeeImg: String,
    val employeePhoneNumbers: List<String>
)
