package com.ifeor.welltecemployeeplanner.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Passed (
    @SerializedName("employeeEmail")
    val employeeEmail: String = "",
    @SerializedName("courseTitle")
    val courseTitle: String = "",
    @SerializedName("passedDate")
    val passedDate: GregorianCalendar = GregorianCalendar()
) : Serializable
