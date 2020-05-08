package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Course (
    @SerializedName("courseTitle")
    val courseTitle: String = "",
    @SerializedName("courseDesc")
    val courseDesc: String = "",
    @SerializedName("coursePeriod")
    val coursePeriod: Int = 0
) : Serializable
