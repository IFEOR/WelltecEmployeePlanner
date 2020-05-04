package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.ui.activities.LoginActivity
import kotlinx.android.synthetic.main.fragment_authorization.*

class AuthorizationFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_auth_button.setOnClickListener {onSignInListener() }
        fragment_auth_sign_up.setOnClickListener { onSignUp() }
    }

    private fun onSignUp() {
        (activity as LoginActivity).toRegistration()
    }

    private fun onSignInListener() {
        if(isValidFields()) {
            // TODO Firebase and password check
            // TODO Navigate to MainActivity
        }
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
        if (fragment_auth_username.text.isEmpty()) {
            fragment_auth_username.error = R.string.error_empty.toString()
            isNotError = false
        }
        if (fragment_auth_password.text.isEmpty()) {
            fragment_auth_password.error = R.string.error_empty.toString()
            isNotError = false
        }
        return isNotError
    }
}
