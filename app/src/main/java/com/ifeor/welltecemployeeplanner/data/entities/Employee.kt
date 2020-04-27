package com.ifeor.welltecemployeeplanner.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Employee {
    @PrimaryKey
    var employeeID: Long = 0
    var employeeFirstName: String = "Default Name"
    var employeeSecondName: String? = null
    var employeeImage: String? = null
    var employeePosition: String? = null
    var employeeRole: String = "user"
    var employeeEmail: String? = "default@email.wep"
    var employeePasswordHash: String? = "defaultPasswordHash"
}
