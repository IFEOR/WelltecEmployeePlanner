package com.ifeor.welltecemployeeplanner.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Site {

    @PrimaryKey
    var siteID: Long = 0
    //@ForeignKey(entity = notificationID, parentColumns = Notification, childColumns = notificationID)
    var locationID: Long = 0
    //@ForeignKey(entity = employeeID, parentColumns = Employee, childColumns = employeeID)
    var employeeID: Long = 0

    //var siteStartDate: DateFormat = DateFormat()
}
