package com.ifeor.welltecemployeeplanner.ui.presenters

import com.ifeor.welltecemployeeplanner.data.repositories.NotificationRepositoryImpl
import com.ifeor.welltecemployeeplanner.ui.views.NotificationListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class NotificationListPresenter: MvpPresenter<NotificationListView>() {

    private val notificationRepository = NotificationRepositoryImpl()

    fun fetchNotifications() {
        viewState.presentLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val notifications = notificationRepository.fetchNotificationAsync().await()
                withContext(Dispatchers.Main) {
                    viewState.presentNotifications(data = notifications)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
