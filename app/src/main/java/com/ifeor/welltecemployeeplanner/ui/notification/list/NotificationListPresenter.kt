package com.ifeor.welltecemployeeplanner.ui.notification.list

import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.repositories.NotificationRepositoryImpl
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
                val user = FirebaseAuth.getInstance().currentUser
                val userEmail: String = user!!.email + ""
                val db = FirestoneDatabase()
                val employee = db.getEmployee(userEmail)
                val notifications = notificationRepository.fetchNotificationAsync().await()
                withContext(Dispatchers.Main) {
                    if (notifications.isNotEmpty()) {
                        viewState.presentNotifications(data = notifications, userRole = employee.employeeRole)
                    } else {
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
