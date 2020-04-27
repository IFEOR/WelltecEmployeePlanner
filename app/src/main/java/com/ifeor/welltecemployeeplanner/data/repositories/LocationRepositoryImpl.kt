package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.model.Location
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LocationRepositoryImpl {

    fun fetchLocationAsync(): Deferred<List<Location>> {
        val mockData = ArrayList<Location>()

        mockData.add(Location(0, "Aksai", "Kazakhstan location"))
        mockData.add(Location(1, "Tyumen", "Russian location"))

        return GlobalScope.async { mockData  }
    }
}
