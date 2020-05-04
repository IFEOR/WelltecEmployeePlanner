package com.ifeor.welltecemployeeplanner.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.ifeor.welltecemployeeplanner.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    
    fun toRegistration() {
        findNavController(R.id.nav_host_login_fragment).navigate(R.id.nav_registration)
    }
    
    fun logIn() {
        // TODO
    }
    
    fun register() {
        // TODO
    }
}
