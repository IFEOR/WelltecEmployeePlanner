package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location (
    @SerializedName("locationTitle")
    val locationTitle: String = "",
    @SerializedName("locationDesc")
    val locationDesc: String = ""
) : Serializable
