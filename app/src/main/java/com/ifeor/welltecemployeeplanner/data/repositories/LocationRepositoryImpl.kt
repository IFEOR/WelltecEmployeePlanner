package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Location
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LocationRepositoryImpl {

    fun fetchLocationAsync(): Deferred<List<Location>> {

        val db = FirestoneDatabase()
        db.getLocations()
        Thread.sleep(2000)
        val data = db.getLocationList()

        return GlobalScope.async { data  }
    }
}
