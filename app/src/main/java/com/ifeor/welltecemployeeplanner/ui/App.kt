package com.ifeor.welltecemployeeplanner.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.room.Room
import com.ifeor.welltecemployeeplanner.data.database.AppDatabase


@SuppressLint("Registered")
class App: Application() {

    private var instance: App? = null

    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
    }

    fun getInstance(): App? {
        return instance
    }

    fun getDatabase(): AppDatabase? {
        return database
    }
}
