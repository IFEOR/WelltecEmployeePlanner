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
import kotlinx.android.synthetic.main.fragment_registration.*

@Suppress("NAME_SHADOWING")
class RegistrationFragment : Fragment() {

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
        if (isValidFields()) {
            (activity as LoginActivity).auth.createUserWithEmailAndPassword(
                fragment_registration_email.text.toString(),
                fragment_registration_password.text.toString()
            )
                .addOnCompleteListener(activity as LoginActivity) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            activity as LoginActivity, getString(R.string.toast_register_success),
                            Toast.LENGTH_SHORT
                        ).show()

                        val user = (activity as LoginActivity).auth.currentUser

                        user!!.sendEmailVerification()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    (activity as LoginActivity).toAuth()
                                }
                            }

                    } else {
                        Toast.makeText(
                            activity as LoginActivity, getString(R.string.toast_register_fail),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
        if (fragment_registration_key.text.isEmpty()) {
            fragment_registration_key.error = getString(R.string.error_empty)
            isNotError = false
        }
        if (fragment_registration_first.text.isEmpty()) {
            fragment_registration_first.error = getString(R.string.error_empty)
            isNotError = false
        }
        if (fragment_registration_second.text.isEmpty()) {
            fragment_registration_second.error = getString(R.string.error_empty)
            isNotError = false
        }
        if (fragment_registration_email.text.isEmpty()) {
            fragment_registration_email.error = getString(R.string.error_empty)
            isNotError = false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(fragment_registration_email.text.toString()).matches()) {
            fragment_registration_email.error = getString(R.string.error_email)
            isNotError = false
        }
        if (fragment_registration_phone.text.isEmpty()) {
            fragment_registration_phone.error = getString(R.string.error_empty)
            isNotError = false
        }
        if (fragment_registration_position.text.isEmpty()) {
            fragment_registration_position.error = getString(R.string.error_empty)
            isNotError = false
        }
        if (fragment_registration_password.text.length < 8) {
            fragment_registration_password.error = getString(R.string.error_short)
            isNotError = false
        }
        if (fragment_registration_re_password.text.toString() != fragment_registration_password.text.toString()) {
            fragment_registration_re_password.error = getString(R.string.error_differ_password)
            isNotError = false
        }
        return isNotError
    }
}
