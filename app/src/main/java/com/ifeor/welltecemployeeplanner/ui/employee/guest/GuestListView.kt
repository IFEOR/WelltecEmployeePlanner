package com.ifeor.welltecemployeeplanner.ui.employee.guest

import com.ifeor.welltecemployeeplanner.data.model.Employee
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface GuestListView : MvpView {

    fun showLoadErrorText()
    fun showNoDataText()
    fun presentGuests(data: List<Employee>)
    fun presentLoading()
}
