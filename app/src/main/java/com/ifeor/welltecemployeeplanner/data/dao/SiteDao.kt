package com.ifeor.welltecemployeeplanner.data.dao

import androidx.room.*
import com.ifeor.welltecemployeeplanner.data.entities.Site

@Dao
interface SiteDao {

    @Query("SELECT * FROM site")
    fun getAll(): List<Site?>?

    @Query("SELECT * FROM site WHERE siteID = :siteId")
    fun getById(siteId: Long): Site?

    @Insert
    fun insert(site: Site?)

    @Update
    fun update(site: Site?)

    @Delete
    fun delete(site: Site?)
}
