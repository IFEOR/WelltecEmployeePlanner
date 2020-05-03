package com.ifeor.welltecemployeeplanner.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.ifeor.welltecemployeeplanner.R
import kotlinx.android.synthetic.main.activity_login.*

@SuppressLint("Registered")
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        activity_login_button_login.setOnClickListener {
            if(isValidFields()) {
                // TODO Firebase and password check
                // TODO Navigate to MainActivity
            }
        }
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
        if (activity_login_username.text.isEmpty()) {
            activity_login_username.error = R.string.fragments_add_error_empty.toString()
            isNotError = false
        }
        if (activity_login_password.text.isEmpty()) {
            activity_login_password.error = R.string.fragments_add_error_empty.toString()
            isNotError = false
        }
        if (activity_login_password.text.length < 8) {
            activity_login_password.error = R.string.fragments_add_error_short.toString()
            isNotError = false
        }
        return isNotError
    }
}

