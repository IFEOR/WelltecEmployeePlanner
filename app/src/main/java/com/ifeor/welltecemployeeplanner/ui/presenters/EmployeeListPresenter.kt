package com.ifeor.welltecemployeeplanner.ui.presenters

import com.ifeor.welltecemployeeplanner.data.repositories.EmployeeRepositoryImpl
import com.ifeor.welltecemployeeplanner.ui.views.EmployeeListView
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
