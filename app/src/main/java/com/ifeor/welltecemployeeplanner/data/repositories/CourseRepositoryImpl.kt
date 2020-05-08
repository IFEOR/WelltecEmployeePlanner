package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Course
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class CourseRepositoryImpl {

    fun fetchCourseAsync(): Deferred<List<Course>> {
        val db = FirestoneDatabase()
        return GlobalScope.async { db.getCourseList()  }
    }
}
