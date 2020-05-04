package com.ifeor.welltecemployeeplanner.ui.presenters

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
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
                    if (!notifications.isEmpty()) {
                        viewState.presentNotifications(data = notifications)
                    } else {
                        if(false) // TODO

                            viewState.showLoadErrorText()
                        else
                            viewState.showNoDataText()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showLoadErrorText()
            }
        }
    }
}
