package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("locationID")
    val locationID: Long = 0,
    @SerializedName("locationTitle")
    val locationTitle: String = "",
    @SerializedName("locationDesc")
    val locationDesc: String = ""
)
