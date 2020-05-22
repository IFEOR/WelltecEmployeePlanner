package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Passed
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class PassedRepositoryImpl {

    fun fetchPassedAsync(): Deferred<MutableList<Passed>> {
        val db = FirestoneDatabase()
        return GlobalScope.async { db.getPassedList() }
    }
}
