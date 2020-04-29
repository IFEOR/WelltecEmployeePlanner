package com.ifeor.welltecemployeeplanner.data.entities

import androidx.room.Entity
import java.text.DateFormat

@Entity
class PassedCourse {

    // @ForeignKey
    var courseID: Long = 0
    // @ForeignKey
    var employeeID: Long = 0

    var passedDate: java.util.Date = java.util.Date(2020, 2, 2)
}
