package com.ifeor.welltecemployeeplanner.ui.employee.list

import com.ifeor.welltecemployeeplanner.data.model.Employee
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface EmployeeListView : MvpView {

    fun showLoadErrorText()
    fun showNoDataText()
    fun presentEmployees(data: List<Employee>, userRole: String)
    fun presentLoading()
    fun setRole(role: String)
}
