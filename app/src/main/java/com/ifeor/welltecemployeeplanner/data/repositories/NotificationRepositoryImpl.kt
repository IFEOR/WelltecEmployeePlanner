package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Notification
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NotificationRepositoryImpl {



    fun fetchNotificationAsync(): Deferred<List<Notification>> {

        val db = FirestoneDatabase()
        db.getNotifications()
        Thread.sleep(2000)
        val data = db.getNotificationList()

        return GlobalScope.async { data  }
    }
}
