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
import kotlinx.android.synthetic.main.fragment_employee_add.*

class EmployeeAddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_employee_add_button.setOnClickListener {
            if (isValidFields()) {
                val firestoneDB = FirestoneDatabase()
                firestoneDB.addEmployee(
                    0,
                    fragment_employee_add_first.text.toString(),
                    fragment_employee_add_second.text.toString(),
                    fragment_employee_add_position.text.toString(),
                    fragment_employee_add_spinner.selectedItem.toString(),
                    fragment_employee_add_email.text.toString(),
                    fragment_employee_add_phone.text.toString()
                )
                (activity as MainActivity).findNavController(R.id.nav_host_fragment).popBackStack()
                Snackbar.make(view, "The employee was added", Snackbar.LENGTH_LONG)
            }
        }
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
        if (fragment_employee_add_first.text.isEmpty()) {
            fragment_employee_add_first.error = R.string.fragments_add_error_empty.toString()
            isNotError = false
        }
        if (fragment_employee_add_second.text.isEmpty()) {
            fragment_employee_add_second.error = R.string.fragments_add_error_empty.toString()
            isNotError = false
        }
        if (fragment_employee_add_email.text.isEmpty()) {
            fragment_employee_add_email.error = R.string.fragments_add_error_empty.toString()
            isNotError = false
        }
        if (fragment_employee_add_phone.text.isEmpty()) {
            fragment_employee_add_phone.error = R.string.fragments_add_error_empty.toString()
            isNotError = false
        }
        if (fragment_employee_add_position.text.isEmpty()) {
            fragment_employee_add_position.error = R.string.fragments_add_error_empty.toString()
            isNotError = false
        }
        return isNotError
    }
}
