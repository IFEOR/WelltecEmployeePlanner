package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Employee
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class GuestListRepositoryImpl {

    fun fetchGuestsAsync(): Deferred<List<Employee>> {
        val db = FirestoneDatabase()
        return GlobalScope.async { db.getGuestList()  }
    }
}
