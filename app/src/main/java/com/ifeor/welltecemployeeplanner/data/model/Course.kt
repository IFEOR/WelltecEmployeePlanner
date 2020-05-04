package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName

data class Course (
    @SerializedName("courseID")
    val courseID: Long = 0,
    @SerializedName("courseTitle")
    val courseTitle: String = "",
    @SerializedName("courseDesc")
    val courseDesc: String = "",
    @SerializedName("coursePeriod")
    val coursePeriod: Int = 0
)
