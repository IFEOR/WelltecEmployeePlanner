package com.ifeor.welltecemployeeplanner.ui.employee.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.data.model.Passed
import com.ifeor.welltecemployeeplanner.data.model.Site
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_employee.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class EmployeeFragment: MvpAppCompatFragment(),
    EmployeeView {

    companion object {
        const val EMPLOYEE_DATA_TAG = "employee"
    }

    @InjectPresenter
    lateinit var employeePresenter: EmployeePresenter

    private val employeeAdapter = EmployeeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val employee = arguments?.getSerializable(EMPLOYEE_DATA_TAG) as Employee
        setupAdapter()
        // employeePresenter.fetchSite(employee.employeeEmail)
        employeePresenter.fetchPassed(employee.employeeEmail)
        setEmployeeCredentials()
        fragment_employee_add_btn.setOnClickListener { setAddButtonClick() }
        fragment_employee_select_btn.setOnClickListener { setSelectButtonClick() }
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        employee_course_recycler.layoutManager = layoutManager
        employee_course_recycler.adapter = employeeAdapter
    }

    private fun setEmployeeCredentials() {
        val employee = arguments?.getSerializable(EMPLOYEE_DATA_TAG) as Employee
        val employeeName = "${employee.employeeFirstName} ${employee.employeeSecondName}"
        fragment_employee_name.text = employeeName
        fragment_employee_email.text = employee.employeeEmail
        fragment_employee_phone.text = employee.employeePhoneNumber
        fragment_employee_position.text = employee.employeePosition
        fragment_employee_role.text = employee.employeeRole
    }

    fun setSelectButtonClick() {
        (activity as MainActivity).toChoiceLocation()
    }

    fun setAddButtonClick() {
        (activity as MainActivity).toChoiceCourse()
    }

    override fun showPassedLoadErrorText() {
        fragment_employee_courses.visibility = View.VISIBLE
        fragment_employee_courses.text = getString(R.string.error_load)
    }

    override fun showSiteLoadErrorText() {
        fragment_employee_location.text = getString(R.string.error_load)
    }

    override fun showPassedNoDataText() {
        fragment_employee_courses.visibility = View.VISIBLE
        fragment_employee_courses.text = getString(R.string.no_courses)
    }

    override fun showSiteNoDataText() {
        fragment_employee_location.text = getString(R.string.no_location)
    }

    override fun presentPassed(data: List<Passed>) {
        fragment_employee_courses.visibility = View.GONE
        employeeAdapter.updateCourses( newCourses = data)
    }

    override fun presentSite(data: List<Site>) {
        fragment_employee_location.text = data[0].locationTitle
    }

    override fun presentPassedLoading() {
        fragment_employee_courses.visibility = View.VISIBLE
        fragment_employee_courses.text = getString(R.string.txt_loading)
    }

    override fun presentSiteLoading() {
        fragment_employee_location.text = getString(R.string.txt_loading)
    }
}
