package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_notification_add.*
import java.text.SimpleDateFormat
import java.util.*

class NotificationAddFragment : Fragment() {

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
            if (isValidFields()) {
                val currentDate = Date()
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val actualDate = dateFormat.format(currentDate)

                val firestoneDB = FirestoneDatabase()
                firestoneDB.addNotification(
                    firestoneDB.db,
                    0,
                    fragment_notification_add_name.text.toString(),
                    fragment_notification_add_desc.text.toString(),
                    actualDate
                )
                (activity as MainActivity).findNavController(R.id.nav_host_fragment).popBackStack()
            }
            Snackbar.make(view, "The notification was added", Snackbar.LENGTH_LONG)
        }
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
        if (fragment_notification_add_name.text.isEmpty()) {
            fragment_notification_add_name.error = R.string.fragments_add_error_empty.toString()
            isNotError = false
        }
        return isNotError
    }
}
