package com.ifeor.welltecemployeeplanner.data.dao

import androidx.room.*
import com.ifeor.welltecemployeeplanner.data.entities.Location

@Dao
interface LocationDao {

    @Query("SELECT * FROM location")
    fun getAll(): List<Location?>?

    @Query("SELECT * FROM location WHERE locationID = :id")
    fun getById(id: Long): Location?

    @Insert
    fun insert(location: Location?)

    @Update
    fun update(location: Location?)

    @Delete
    fun delete(location: Location?)
}
