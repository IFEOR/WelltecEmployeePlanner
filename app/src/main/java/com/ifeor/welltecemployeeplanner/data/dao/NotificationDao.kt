package com.ifeor.welltecemployeeplanner.data.dao

import androidx.room.*
import com.ifeor.welltecemployeeplanner.data.entities.Notification

@Dao
interface NotificationDao {

    @Query("SELECT * FROM notification")
    fun getAll(): List<Notification?>?

    @Query("SELECT * FROM notification WHERE notificationID = :id")
    fun getById(id: Long): Notification?

    @Insert
    fun insert(notification: Notification?)

    @Update
    fun update(notification: Notification?)

    @Delete
    fun delete(notification: Notification?)
}
