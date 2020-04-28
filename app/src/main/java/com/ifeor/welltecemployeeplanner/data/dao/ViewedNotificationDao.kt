package com.ifeor.welltecemployeeplanner.data.dao

import androidx.room.*
import com.ifeor.welltecemployeeplanner.data.entities.ViewedNotification

@Dao
interface ViewedNotificationDao {

    @Query("SELECT * FROM viewedNotification")
    fun getAll(): List<ViewedNotification?>?

    @Query("SELECT * FROM viewedNotification WHERE notificationID = :notId and employeeID = :empId")
    fun getById(empId: Long, notId: Long): ViewedNotification?

    @Insert
    fun insert(viewedNotification: ViewedNotification?)

    @Update
    fun update(viewedNotification: ViewedNotification?)

    @Delete
    fun delete(viewedNotification: ViewedNotification?)
}
