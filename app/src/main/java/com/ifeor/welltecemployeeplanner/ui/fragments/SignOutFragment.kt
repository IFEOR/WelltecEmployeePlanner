package com.ifeor.welltecemployeeplanner.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.ui.activities.LoginActivity
import com.ifeor.welltecemployeeplanner.ui.views.SignOutView
import kotlinx.android.synthetic.main.fragment_signout.*

class SignOutFragment : Fragment(), SignOutView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_signout_btn.setOnClickListener { logOut() }
    }

    override fun logOut() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(context, LoginActivity::class.java))
    }
}
