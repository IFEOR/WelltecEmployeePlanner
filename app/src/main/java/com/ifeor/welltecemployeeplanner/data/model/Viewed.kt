package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Viewed (
    @SerializedName("employeeEmail")
    val employeeEmail: String = "",
    @SerializedName("notificationID")
    val notificationID: String = ""
) : Serializable
