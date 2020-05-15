package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Site (
    @SerializedName("employeeEmail")
    val employeeEmail: String = "",
    @SerializedName("locationTitle")
    val locationTitle: String = ""
): Serializable
