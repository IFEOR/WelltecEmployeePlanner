package com.ifeor.welltecemployeeplanner.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Location {
    @PrimaryKey
    var locationID: Long = 0
    var locationTitle: String = "DefaultTitle"
    var locationDesc: String? = null
}
