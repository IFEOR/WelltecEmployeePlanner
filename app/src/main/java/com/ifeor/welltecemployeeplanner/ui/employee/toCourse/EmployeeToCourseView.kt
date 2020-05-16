package com.ifeor.welltecemployeeplanner.ui.employee.toCourse

import com.ifeor.welltecemployeeplanner.data.model.Employee
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface EmployeeToCourseView : MvpView {

    fun showLoadErrorText()
    fun showNoDataText()
    fun presentEmployees(data: List<Employee>)
    fun presentLoading()
}
