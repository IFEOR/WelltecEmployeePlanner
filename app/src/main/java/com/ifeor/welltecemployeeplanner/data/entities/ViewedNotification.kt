package com.ifeor.welltecemployeeplanner.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity
class ViewedNotification {
    //@ForeignKey(entity = notificationID, parentColumns = Notification, childColumns = notificationID)
    var notificationID: Long = 0
    //@ForeignKey(entity = employeeID, parentColumns = Employee, childColumns = employeeID)
    var employeeID: Long = 0
}
