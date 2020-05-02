package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName

data class Employee (
    @SerializedName("employeeID")
    val employeeID: Long,
    @SerializedName("employeeFirstName")
    val employeeFirstName: String,
    @SerializedName("employeeSecondName")
    val employeeSecondName: String,
    @SerializedName("employeePosition")
    val employeePosition: String,
    @SerializedName("employeeRole")
    val employeeRole: String,
    @SerializedName("employeeEmail")
    val employeeEmail: String,
    @SerializedName("employeePhone")
    val employeePhone: String
)
