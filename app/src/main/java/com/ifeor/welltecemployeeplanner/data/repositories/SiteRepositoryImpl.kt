package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Site
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class SiteRepositoryImpl {

    fun fetchSiteAsync(): Deferred<MutableList<Site>> {
        val db = FirestoneDatabase()
        return GlobalScope.async { db.getSiteList() }
    }
}
