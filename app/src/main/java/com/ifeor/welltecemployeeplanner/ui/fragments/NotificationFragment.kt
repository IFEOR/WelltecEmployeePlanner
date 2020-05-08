package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Notification
import com.ifeor.welltecemployeeplanner.ui.presenters.NotificationPresenter
import com.ifeor.welltecemployeeplanner.ui.views.NotificationView
import kotlinx.android.synthetic.main.fragment_notification.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class NotificationFragment: MvpAppCompatFragment(), NotificationView {

    companion object {
        const val NOTIFICATION_DATA_TAG = "notification"
    }

    @InjectPresenter
    lateinit var notificationPresenter: NotificationPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNotificationCredentials()
    }

    private fun setNotificationCredentials() {
        val notification = arguments?.getSerializable(NOTIFICATION_DATA_TAG) as Notification
        fragment_notification_title.text = notification.notificationTitle
        fragment_notification_desc.text = notification.notificationDesc
        fragment_notification_date.text = notification.notificationDate

    }
}
