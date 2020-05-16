package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Site
import com.ifeor.welltecemployeeplanner.data.model.Viewed
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ViewedRepositoryImpl {

    fun fetchViewedAsync(): Deferred<List<Viewed>> {
        val db = FirestoneDatabase()
        return GlobalScope.async { db.getViewedList() }
    }
}
