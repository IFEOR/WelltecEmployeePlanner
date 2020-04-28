package com.ifeor.welltecemployeeplanner.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ifeor.welltecemployeeplanner.data.dao.*
import com.ifeor.welltecemployeeplanner.data.entities.*


@Database(entities = [
    Course::class,
    Employee::class,
    Location::class,
    Notification::class,
    PassedCourse::class,
    PhoneNumber::class,
    Site::class,
    ViewedNotification::class
], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun courseDao(): CourseDao?
    abstract fun employeeDao(): EmployeeDao?
    abstract  fun locationDao(): LocationDao?
    abstract  fun notificationDao(): NotificationDao?
    abstract  fun passedCourseDao(): PassedCourseDao?
    abstract  fun phoneNumberDao(): PhoneNumberDao?
    abstract  fun siteDao(): SiteDao?
    abstract  fun viewedNotificationDao(): ViewedNotificationDao?
}
