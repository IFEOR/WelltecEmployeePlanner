package com.ifeor.welltecemployeeplanner.ui.notification.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Notification
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_notification_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class NotificationListFragment : MvpAppCompatFragment(),
    NotificationListView {

    @InjectPresenter
    lateinit var notificationListPresenter: NotificationListPresenter

    private val notificationListAdapter =
        NotificationListAdapter(
            ::onNotificationClick
        )

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

    private fun onNotificationClick(notification: Notification) {
        val user = FirebaseAuth.getInstance().currentUser
        val userEmail: String = user!!.email + ""
        (activity as MainActivity).chosenNotification = notification.notificationID
        (activity as MainActivity).addViewed(userEmail)
        (context as MainActivity).openNotificationScreen(notification)
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

    override fun presentNotifications(data: List<Notification>, userRole: String) {
        fragment_notification_list_loading.visibility = View.GONE
        if (userRole == "Guest") {
            notification_recycler.visibility = View.GONE
            fragment_notifications_textview_guest.visibility = View.VISIBLE
        } else {
            notification_recycler.visibility = View.VISIBLE
            fragment_notifications_textview_guest.visibility = View.GONE
        }
        setRole(userRole)
        notificationListAdapter.updateNotifications(newNotifications = data)
    }

    override fun setRole(role: String) {
        if (role != "Coordinator") {
            action_add_notification.visibility = View.GONE
        } else {
            action_add_notification.visibility = View.VISIBLE
        }
    }

    override fun presentLoading() {
        action_add_notification.visibility = View.GONE
        fragment_notifications_textview_load_error.visibility = View.GONE
        fragment_notifications_textview_nodata.visibility = View.GONE
        fragment_notification_list_loading.visibility = View.VISIBLE
        notification_recycler.visibility = View.GONE
        fragment_notifications_textview_guest.visibility = View.GONE
    }
}
