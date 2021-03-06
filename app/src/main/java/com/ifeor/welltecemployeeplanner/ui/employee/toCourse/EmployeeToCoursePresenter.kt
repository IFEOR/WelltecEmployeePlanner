package com.ifeor.welltecemployeeplanner.ui.employee.toCourse

import com.ifeor.welltecemployeeplanner.data.repositories.EmployeeRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class EmployeeToCoursePresenter: MvpPresenter<EmployeeToCourseView>() {

    private val employeeRepository = EmployeeRepositoryImpl()

    fun fetchEmployees() {
        viewState.presentLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val employees = employeeRepository.fetchEmployeesAsync().await()
                withContext(Dispatchers.Main) {
                    if (employees.isNotEmpty()) {
                        viewState.presentEmployees(data = employees)
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
