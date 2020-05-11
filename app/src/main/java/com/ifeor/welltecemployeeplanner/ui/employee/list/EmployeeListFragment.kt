package com.ifeor.welltecemployeeplanner.ui.employee.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_employee_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        setRole()
    }

    private fun setRole() {
        val user = FirebaseAuth.getInstance().currentUser
        val userEmail: String = user!!.email + ""
        val db = FirestoneDatabase()
        var userRole = ""
        GlobalScope.launch {
            userRole = withContext(Dispatchers.IO) {
                db.getEmployee(userEmail).employeeRole
            }
        }
        if (userRole != "Coordinator") {
            action_to_guests.visibility = View.GONE
        } else {
            action_to_guests.visibility = View.VISIBLE
        }
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

    override fun presentEmployees(data: List<Employee>) {
        fragment_employee_list_loading.visibility = View.GONE
        employee_recycler.visibility = View.VISIBLE
        employeeListAdapter.updateEmployees(newEmployees = data)
    }

    override fun presentLoading() {
        fragment_employee_list_loading.visibility = View.VISIBLE
        employee_recycler.visibility = View.GONE
    }
}
