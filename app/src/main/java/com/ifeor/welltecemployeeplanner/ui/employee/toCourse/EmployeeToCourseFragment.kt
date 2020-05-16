package com.ifeor.welltecemployeeplanner.ui.employee.toCourse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_choice_employee.*
import kotlinx.android.synthetic.main.fragment_employee_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class EmployeeToCourseFragment : MvpAppCompatFragment(),
    EmployeeToCourseView {

    @InjectPresenter
    lateinit var employeeToCoursePresenter: EmployeeToCoursePresenter

    private val employeeToCourseAdapter =
        EmployeeToCourseAdapter(::onEmployeeClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choice_employee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        employeeToCoursePresenter.fetchEmployees()
    }

    private fun onEmployeeClick(employee: Employee) {
        if (isValidFields()) {
            (context as MainActivity).addPassedByEmployee(
                employee.employeeEmail,
                fragment_choice_employee_passed_date.text.toString()
            )
        }
    }

    override fun showLoadErrorText() {
        fragment_choice_employee_loading.visibility = View.GONE
        fragment_choice_employee_textview_load_error.visibility = View.VISIBLE
    }

    override fun showNoDataText() {
        fragment_choice_employee_loading.visibility = View.GONE
        fragment_choice_employee_textview_nodata.visibility = View.VISIBLE
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        choice_employee_recycler.layoutManager = layoutManager
        choice_employee_recycler.adapter = employeeToCourseAdapter
    }

    override fun presentEmployees(data: List<Employee>) {
        fragment_employee_list_loading.visibility = View.GONE
        choice_employee_recycler.visibility = View.VISIBLE
        employeeToCourseAdapter.updateEmployees(newEmployees = data)
    }

    override fun presentLoading() {
        fragment_choice_employee_loading.visibility = View.VISIBLE
        choice_employee_recycler.visibility = View.GONE
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
        if (fragment_choice_employee_passed_date.text.isEmpty()) {
            fragment_choice_employee_passed_date.error = getString(R.string.error_empty)
            isNotError = false
        }
        return isNotError
    }
}
