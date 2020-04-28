package com.ifeor.welltecemployeeplanner.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PhoneNumber {

    @PrimaryKey
    var phoneID: Long = 0
    // @ForeignKey (not identy)
    var employeeID: Long = 0

    var phoneNumber: String = "+7(000)000-00-00"
}
