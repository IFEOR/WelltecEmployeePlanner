package com.ifeor.welltecemployeeplanner.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ifeor.welltecemployeeplanner.R

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, MainActivity::class.java))
                // finish()
            } else {
                Toast.makeText(
                    baseContext, getString(R.string.toast_verify_email),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun toRegistration() {
        findNavController(R.id.nav_host_login_fragment).navigate(R.id.nav_registration)
    }

    fun toAuth() {
        findNavController(R.id.nav_host_login_fragment).navigate(R.id.nav_authorization)
    }
}
