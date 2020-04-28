package com.ifeor.welltecemployeeplanner.data.dao

import androidx.room.*
import com.ifeor.welltecemployeeplanner.data.entities.PhoneNumber

@Dao
interface PhoneNumberDao {
    @Query("SELECT * FROM phoneNumber")
    fun getAll(): List<PhoneNumber?>?

    @Query("SELECT * FROM phoneNumber WHERE phoneID = :phoneId")
    fun getById(phoneId: Long): PhoneNumber?

    @Query("SELECT * FROM phoneNumber WHERE employeeID = :employeeId")
    fun getByEmployee(employeeId: Long): PhoneNumber?

    @Insert
    fun insert(phoneNumber: PhoneNumber?)

    @Update
    fun update(phoneNumber: PhoneNumber?)

    @Delete
    fun delete(phoneNumber: PhoneNumber?)
}
