package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Notification
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NotificationRepositoryImpl {

    fun fetchNotificationAsync(): Deferred<List<Notification>> {
        val db = FirestoneDatabase()
        return GlobalScope.async { db.getNotificationList()  }
    }
}
