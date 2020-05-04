package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName

data class Notification (
    @SerializedName("notificationID")
    val notificationID: Long = 0,
    @SerializedName("notificationTitle")
    val notificationTitle: String = "",
    @SerializedName("notificationDesc")
    val notificationDesc: String = "",
    @SerializedName("notificationDate")
    val notificationDate: String = ""
)
