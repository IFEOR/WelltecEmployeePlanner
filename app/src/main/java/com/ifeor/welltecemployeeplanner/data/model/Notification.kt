package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Notification (
    @SerializedName("notificationTitle")
    val notificationTitle: String = "",
    @SerializedName("notificationDesc")
    val notificationDesc: String = "",
    @SerializedName("notificationDate")
    val notificationDate: String = ""
) : Serializable
