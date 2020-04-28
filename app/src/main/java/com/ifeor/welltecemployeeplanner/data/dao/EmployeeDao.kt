package com.ifeor.welltecemployeeplanner.data.dao

import androidx.room.*
import com.ifeor.welltecemployeeplanner.data.entities.Employee

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee")
    fun getAll(): List<Employee?>?

    @Query("SELECT * FROM employee WHERE employeeID = :id")
    fun getById(id: Long): Employee?

    @Insert
    fun insert(employee: Employee?)

    @Update
    fun update(employee: Employee?)

    @Delete
    fun delete(employee: Employee?)
}
