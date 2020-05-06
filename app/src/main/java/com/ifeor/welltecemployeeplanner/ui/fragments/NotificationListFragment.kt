package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Notification
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import com.ifeor.welltecemployeeplanner.ui.adapters.NotificationListAdapter
import com.ifeor.welltecemployeeplanner.ui.presenters.NotificationListPresenter
import com.ifeor.welltecemployeeplanner.ui.views.NotificationListView
import kotlinx.android.synthetic.main.fragment_notification_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class NotificationListFragment : MvpAppCompatFragment(),
    NotificationListView {

    @InjectPresenter
    lateinit var notificationListPresenter: NotificationListPresenter

    private val notificationListAdapter = NotificationListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        notificationListPresenter.fetchNotifications()

        action_add_notification.setOnClickListener { (activity as MainActivity).toAddNotification() }
    }


    override fun showNoDataText() {
        fragment_notification_list_loading.visibility = View.GONE
        fragment_notifications_textview_nodata.visibility = View.VISIBLE
    }

    override fun showLoadErrorText() {
        fragment_notification_list_loading.visibility = View.GONE
        fragment_notifications_textview_load_error.visibility = View.VISIBLE
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        notification_recycler.layoutManager = layoutManager
        notification_recycler.adapter = notificationListAdapter
    }

    override fun presentNotifications(data: List<Notification>) {
        fragment_notification_list_loading.visibility = View.GONE
        notification_recycler.visibility = View.VISIBLE
        notificationListAdapter.updateNotifications(newNotifications = data)
    }

    override fun presentLoading() {
        fragment_notifications_textview_load_error.visibility = View.GONE
        fragment_notifications_textview_nodata.visibility = View.GONE
        fragment_notification_list_loading.visibility = View.VISIBLE
        notification_recycler.visibility = View.GONE
    }
}
