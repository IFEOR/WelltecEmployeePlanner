package com.ifeor.welltecemployeeplanner.data.dao

import androidx.room.*
import com.ifeor.welltecemployeeplanner.data.entities.Course

@Dao
interface CourseDao {

    @Query("SELECT * FROM course")
    fun getAll(): List<Course?>?

    @Query("SELECT * FROM course WHERE courseID = :id")
    fun getById(id: Long): Course?

    @Insert
    fun insert(course: Course?)

    @Update
    fun update(course: Course?)

    @Delete
    fun delete(course: Course?)
}
