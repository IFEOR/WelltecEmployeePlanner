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
import kotlinx.android.synthetic.main.fragment_course_add.*

class CourseAddFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_course_add_button.setOnClickListener {
            if (isValidFields()) {
                val firestoneDB = FirestoneDatabase()
                firestoneDB.addCourse(
                    firestoneDB.db,
                    0,
                    fragment_course_add_name.text.toString(),
                    fragment_course_add_desc.text.toString(),
                    Integer.parseInt(fragment_course_add_period.toString())
                )
                (activity as MainActivity).findNavController(R.id.nav_host_fragment).popBackStack()
            }
            Snackbar.make(view, "The course was added", Snackbar.LENGTH_LONG)
        }
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
        if (fragment_course_add_name.text.isEmpty()) {
            fragment_course_add_name.error = R.string.fragments_add_error_empty.toString()
            isNotError = false
        }
        if (fragment_course_add_period.text.isEmpty()) {
            fragment_course_add_period.error = R.string.fragments_add_error_empty.toString()
            isNotError = false
        }
        return isNotError
    }
}
