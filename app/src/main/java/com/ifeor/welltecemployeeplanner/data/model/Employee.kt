package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Employee (
    @SerializedName("employeeID")
    val employeeID: Long = 0,
    @SerializedName("employeeFirstName")
    val employeeFirstName: String = "",
    @SerializedName("employeeSecondName")
    val employeeSecondName: String = "",
    @SerializedName("employeePosition")
    val employeePosition: String = "",
    @SerializedName("employeeString")
    val employeeRole: String = "",
    @SerializedName("employeeEmail")
    val employeeEmail: String = "",
    @SerializedName("employeePhoneNumber")
    val employeePhoneNumber: String = ""
) : Serializable
