package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.ui.presenters.NotificationListPresenter
import com.ifeor.welltecemployeeplanner.ui.views.NotificationListView
import kotlinx.android.synthetic.main.fragment_notification_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class NotificationListFragment : MvpAppCompatFragment(),
    NotificationListView {

    @InjectPresenter
    lateinit var notificationListPresenter: NotificationListPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification_list, container, false)
    }

    override fun showNoDataText() {
        fragment_notifications_textview_nodata.text = (R.string.fragment_notifications_no_data_text).toString()
    }
}
