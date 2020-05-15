package com.ifeor.welltecemployeeplanner.ui.employee.choice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_choice_employee.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class EmployeeToLocationFragment: MvpAppCompatFragment(),
    EmployeeToLocationView {

    @InjectPresenter
    lateinit var employeeToLocationPresenter: EmployeeToLocationPresenter

    private val employeeToLocationAdapter =
        EmployeeToLocationAdapter(
            ::onEmployeeClick
        )

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
        employeeToLocationPresenter.fetchEmployees()
    }

    private fun onEmployeeClick(employee: Employee) {
        (activity as MainActivity).addSite(employee.employeeEmail)
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
        choice_employee_recycler.adapter = employeeToLocationAdapter
    }

    override fun presentEmployees(data: List<Employee>) {
        fragment_choice_employee_loading.visibility = View.GONE
        choice_employee_recycler.visibility = View.VISIBLE
        employeeToLocationAdapter.updateEmployees(newEmployees = data)
    }

    override fun presentLoading() {
        fragment_choice_employee_loading.visibility = View.VISIBLE
        choice_employee_recycler.visibility = View.GONE
    }
}
