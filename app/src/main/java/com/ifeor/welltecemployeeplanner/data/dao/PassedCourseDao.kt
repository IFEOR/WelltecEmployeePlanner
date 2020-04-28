package com.ifeor.welltecemployeeplanner.data.dao

import androidx.room.*
import com.ifeor.welltecemployeeplanner.data.entities.PassedCourse

@Dao
interface PassedCourseDao {

    @Query("SELECT * FROM passedCourse")
    fun getAll(): List<PassedCourse?>?

    @Query("SELECT * FROM passedCourse WHERE courseID = :courseId and employeeID = :empId")
    fun getById(empId: Long, courseId: Long): PassedCourse?

    @Insert
    fun insert(passedCourse: PassedCourse?)

    @Update
    fun update(passedCourse: PassedCourse?)

    @Delete
    fun delete(passedCourse: PassedCourse?)
}
