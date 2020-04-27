package com.ifeor.welltecemployeeplanner.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.text.DateFormat
import java.util.*

@Entity
class Notification {
    @PrimaryKey
    var notificationID: Long = 0
    var notificationTitle: String = "DefaultTitle"
    var notificationDesc: String? = null
    var notificationDate: GregorianCalendar = GregorianCalendar(2020, 3, 27)
    //var notificationDate: DateFormat = DateFormat()
}
