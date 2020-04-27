package com.ifeor.welltecemployeeplanner.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Course {
    @PrimaryKey
    var courseID: Long = 0
    var courseTitle: String = "DefaulTitle"
    var courseDesc: String? = null
    var coursePeriod: Int = 0
}
