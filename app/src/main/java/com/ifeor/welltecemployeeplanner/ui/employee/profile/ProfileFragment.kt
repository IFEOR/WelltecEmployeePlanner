package com.ifeor.welltecemployeeplanner.ui.employee.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Passed
import com.ifeor.welltecemployeeplanner.data.model.Site
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ProfileFragment: MvpAppCompatFragment(), ProfileView {

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter

    private val profileAdapter = ProfileAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        profilePresenter.fetchEmployee()
        profilePresenter.fetchSite()
        profilePresenter.fetchPassed()
        fragment_profile_add_btn.setOnClickListener { setAddButtonClick() }
        fragment_profile_select_btn.setOnClickListener { setSelectButtonClick() }
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        profile_course_recycler.layoutManager = layoutManager
        profile_course_recycler.adapter = profileAdapter
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

    fun setSelectButtonClick() {
        (activity as MainActivity).toChoiceLocation()
    }

    fun setAddButtonClick() {
        (activity as MainActivity).toChoiceCourse()
    }

    override fun showPassedLoadErrorText() {
        fragment_profile_courses.visibility = View.VISIBLE
        fragment_profile_courses.text = getString(R.string.error_load)
    }

    override fun showSiteLoadErrorText() {
        fragment_profile_location.text = getString(R.string.error_load)
    }

    override fun showPassedNoDataText() {
        fragment_profile_courses.visibility = View.VISIBLE
        fragment_profile_courses.text = getString(R.string.no_courses)
    }

    override fun showSiteNoDataText() {
        fragment_profile_location.text = getString(R.string.no_location)
    }

    override fun presentPassed(data: List<Passed>) {
        fragment_profile_courses.visibility = View.GONE
        profileAdapter.updateCourses( newCourses = data)
    }

    override fun presentSite(data: List<Site>) {
        fragment_profile_location.text = data[0].locationTitle
    }

    override fun presentPassedLoading() {
        fragment_profile_courses.visibility = View.VISIBLE
        fragment_profile_courses.text = getString(R.string.txt_loading)
    }

    override fun presentSiteLoading() {
        fragment_profile_location.text = getString(R.string.txt_loading)
    }
}
