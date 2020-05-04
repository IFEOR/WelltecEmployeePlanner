package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ifeor.welltecemployeeplanner.R
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_registration_button.setOnClickListener { onSignUpListener() }
    }

    private fun onSignUpListener() {
        if(isValidFields()) {

        }
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
        if (fragment_registration_key.text.isEmpty()) {
            fragment_registration_key.error = R.string.error_empty.toString()
            isNotError = false
        }
        if (fragment_registration_first.text.isEmpty()) {
            fragment_registration_first.error = R.string.error_empty.toString()
            isNotError = false
        }
        if (fragment_registration_second.text.isEmpty()) {
            fragment_registration_second.error = R.string.error_empty.toString()
            isNotError = false
        }
        if (fragment_registration_email.text.isEmpty()) {
            fragment_registration_email.error = R.string.error_empty.toString()
            isNotError = false
        }
        if (fragment_registration_phone.text.isEmpty()) {
            fragment_registration_phone.error = R.string.error_empty.toString()
            isNotError = false
        }
        if (fragment_registration_position.text.isEmpty()) {
            fragment_registration_position.error = R.string.error_empty.toString()
            isNotError = false
        }
        if (fragment_registration_password.text.length < 8) {
            fragment_registration_password.error = R.string.error_short.toString()
            isNotError = false
        }
        if (fragment_registration_re_password.text != fragment_registration_password.text) {
            fragment_registration_re_password.error = R.string.error_differ_password.toString()
            isNotError = false
        }
        return isNotError
    }
}
