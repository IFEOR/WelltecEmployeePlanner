package com.ifeor.welltecemployeeplanner.ui.employee.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Employee
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEmployeeCredentials()
        fragment_employee_add_btn.setOnClickListener { setAddButtonClick() }
        fragment_employee_select_btn.setOnClickListener { setSelectButtonClick() }
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
}
