package com.ifeor.welltecemployeeplanner.ui.employee.choice

import android.util.Log
import com.ifeor.welltecemployeeplanner.data.repositories.EmployeeRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class EmployeeToLocationPresenter: MvpPresenter<EmployeeToLocationView>() {

    private val employeeRepository = EmployeeRepositoryImpl()

    fun fetchEmployees() {
        Log.d("Loading", "preLOAD")
        viewState.presentLoading()
        Log.d("Loading", "postLOAD")

        GlobalScope.launch (Dispatchers.IO) {
            try {
                Log.d("Loading", "try")

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
