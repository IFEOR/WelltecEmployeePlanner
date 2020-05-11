package com.ifeor.welltecemployeeplanner.ui.employee.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifeor.welltecemployeeplanner.R
import kotlinx.android.synthetic.main.fragment_profile.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ProfileFragment: MvpAppCompatFragment(), ProfileView {

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profilePresenter.fetchEmployee()
    }

    override fun presentEmployee(
        firstName: String,
        secondName: String,
        position: String,
        phone: String,
        email: String,
        role: String
    ) {
        fragment_profile_loading.visibility = View.GONE
        fragment_profile_information.visibility = View.VISIBLE
        val name = "$firstName $secondName"
        fragment_profile_name.text = name
        fragment_profile_email.text = email
        fragment_profile_phone.text = phone
        fragment_profile_position.text = position
        fragment_profile_role.text = role
    }

    override fun presentLoading() {
        fragment_profile_loading.visibility = View.VISIBLE
        fragment_profile_information.visibility = View.GONE
    }
}
