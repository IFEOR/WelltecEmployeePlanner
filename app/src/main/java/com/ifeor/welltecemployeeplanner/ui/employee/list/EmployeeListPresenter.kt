package com.ifeor.welltecemployeeplanner.ui.employee.list

import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.repositories.EmployeeRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class EmployeeListPresenter: MvpPresenter<EmployeeListView>() {

    private val employeeRepository = EmployeeRepositoryImpl()

    fun fetchEmployees() {
        viewState.presentLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val user = FirebaseAuth.getInstance().currentUser
                val userEmail: String = user!!.email + ""
                val db = FirestoneDatabase()
                val employee = db.getEmployee(userEmail)
                val employees = employeeRepository.fetchEmployeesAsync().await()
                withContext(Dispatchers.Main) {
                    if (employees.isNotEmpty()) {
                        viewState.presentEmployees(data = employees, userRole = employee.employeeRole)
                    } else {
                        viewState.showNoDataText()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showLoadErrorText()
            }
        }
    }
}
