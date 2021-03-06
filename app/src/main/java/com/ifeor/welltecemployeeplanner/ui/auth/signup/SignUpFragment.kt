package com.ifeor.welltecemployeeplanner.ui.auth.signup

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.ui.activities.AuthActivity
import kotlinx.android.synthetic.main.fragment_registration.*

@Suppress("NAME_SHADOWING")
class SignUpFragment : Fragment() {

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
            (activity as AuthActivity).auth.createUserWithEmailAndPassword(
                fragment_registration_email.text.toString(),
                fragment_registration_password.text.toString()
            )
                .addOnCompleteListener(activity as AuthActivity) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            activity as AuthActivity,
                            getString(R.string.toast_register_success),
                            Toast.LENGTH_SHORT
                        ).show()

                        val user = (activity as AuthActivity).auth.currentUser

                        saveUserInformation()

                        user!!.sendEmailVerification()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    (activity as AuthActivity).toAuth()
                                }
                            }

                    } else {
                        Toast.makeText(
                            activity as AuthActivity, getString(R.string.toast_register_fail),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun saveUserInformation() {

        val first = fragment_registration_first.text.toString()
        val second = fragment_registration_second.text.toString()
        val position = fragment_registration_position.text.toString()
        val email = fragment_registration_email.text.toString()
        val phone = fragment_registration_phone.text.toString()

        val db = FirestoneDatabase()
        db.addGuest(
            firstName = first,
            secondName = second,
            position = position,
            email = email,
            phoneNumber = phone
        )
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
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
