package com.ifeor.welltecemployeeplanner.ui.notification.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Notification
import kotlinx.android.synthetic.main.item_notification.view.*
import java.util.*

class NotificationListAdapter(private val onClick: (notification: Notification) -> Unit) :
    RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder>() {

    private val notifications: MutableList<Notification> = LinkedList()

    fun updateNotifications(newNotifications: List<Notification>) {
        notifications.clear()
        notifications.addAll(newNotifications)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        NotificationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        )

    override fun getItemCount() = notifications.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val country = notifications[position]
        holder.bind(country, onClick)
    }

    class NotificationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val notificationTitle = view.item_notification_title
        private val notificationDesc = view.item_notification_desc
        private val notificationDate = view.item_notification_date

        fun bind(country: Notification, onClick: (notification: Notification) -> Unit) {
            val notification = Notification(
                country.notificationTitle,
                country.notificationDesc,
                country.notificationDate
            )
            itemView.setOnClickListener { onClick(notification) }
            notificationTitle.text = country.notificationTitle
            notificationDesc.text = country.notificationDesc
            notificationDate.text = country.notificationDate
            if (notificationDesc.text == "") notificationDesc.visibility = View.GONE
        }
    }
}
