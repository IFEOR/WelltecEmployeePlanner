package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import kotlinx.android.synthetic.main.fragment_course_add.*
import kotlinx.android.synthetic.main.fragment_notification_add.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NotificationAddFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_notification_add_button.setOnClickListener {

            val currentDate = Date()
            val dateFormat =  SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            val actualDate = dateFormat.format(currentDate);

            val firestoneDB = FirestoneDatabase()
            firestoneDB.addNotification(
                firestoneDB.connectDB(),
                0,
                fragment_notification_add_name.text.toString(),
                fragment_notification_add_desc.text.toString(),
                actualDate
            )
        }
    }
}
