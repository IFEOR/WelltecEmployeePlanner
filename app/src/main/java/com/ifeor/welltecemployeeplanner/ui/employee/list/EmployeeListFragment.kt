package com.ifeor.welltecemployeeplanner.ui.employee.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_employee_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class EmployeeListFragment : MvpAppCompatFragment(),
    EmployeeListView {

    @InjectPresenter
    lateinit var employeeListPresenter: EmployeeListPresenter

    private val employeeListAdapter =
        EmployeeListAdapter(::onEmployeeClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        employeeListPresenter.fetchEmployees()
        action_to_guests.setOnClickListener { (activity as MainActivity).toGuests() }
    }

    private fun onEmployeeClick(employee: Employee) {
        (context as MainActivity).openEmployeeScreen(employee)
    }

    override fun showLoadErrorText() {
        fragment_employee_list_loading.visibility = View.GONE
        fragment_employees_textview_load_error.visibility = View.VISIBLE
    }

    override fun showNoDataText() {
        fragment_employee_list_loading.visibility = View.GONE
        fragment_employees_textview_nodata.visibility = View.VISIBLE
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        employee_recycler.layoutManager = layoutManager
        employee_recycler.adapter = employeeListAdapter
    }

    override fun presentEmployees(data: List<Employee>, userRole: String) {
        fragment_employee_list_loading.visibility = View.GONE
        if (userRole == "Guest") {
            employee_recycler.visibility = View.GONE
            fragment_employees_textview_guest.visibility = View.VISIBLE
        } else {
            employee_recycler.visibility = View.VISIBLE
            fragment_employees_textview_guest.visibility = View.GONE
        }
        setRole(userRole)
        employeeListAdapter.updateEmployees(newEmployees = data)
    }

    override fun setRole(role: String) {
        if (role != "Coordinator") {
            action_to_guests.visibility = View.GONE
        } else {
            action_to_guests.visibility = View.VISIBLE
        }
    }

    override fun presentLoading() {
        action_to_guests.visibility = View.GONE
        fragment_employee_list_loading.visibility = View.VISIBLE
        employee_recycler.visibility = View.GONE
        fragment_employees_textview_guest.visibility = View.GONE
    }
}
