package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.ui.activities.LoginActivity
import kotlinx.android.synthetic.main.fragment_authorization.*

class AuthorizationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_authorization, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_auth_button.setOnClickListener { onSignInListener() }
        fragment_auth_sign_up.setOnClickListener { onSignUp() }
    }

    private fun onSignUp() {
        (activity as LoginActivity).toRegistration()
    }

    private fun onSignInListener() {
        if (isValidFields()) {
            (activity as LoginActivity).auth.signInWithEmailAndPassword(
                fragment_auth_username.text.toString(),
                fragment_auth_password.text.toString()
            )
                .addOnCompleteListener(activity as LoginActivity) { task ->
                    if (task.isSuccessful) {
                        val user = (activity as LoginActivity).auth.currentUser
                        (activity as LoginActivity).updateUI(user)
                    } else {
                        Toast.makeText(
                            activity as LoginActivity, getString(R.string.toast_auth_fail),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
        if (fragment_auth_username.text.isEmpty()) {
            fragment_auth_username.error = getString(R.string.error_empty)
            isNotError = false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(fragment_auth_username.text.toString()).matches()) {
            fragment_auth_username.error = getString(R.string.error_email)
            isNotError = false
        }
        if (fragment_auth_password.text.isEmpty()) {
            fragment_auth_password.error = getString(R.string.error_empty)
            isNotError = false
        }
        return isNotError
    }
}
