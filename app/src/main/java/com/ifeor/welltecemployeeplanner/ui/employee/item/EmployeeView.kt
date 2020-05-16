package com.ifeor.welltecemployeeplanner.ui.employee.item

import com.ifeor.welltecemployeeplanner.data.model.Passed
import com.ifeor.welltecemployeeplanner.data.model.Site
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface EmployeeView: MvpView {
    fun showPassedLoadErrorText()
    fun showSiteLoadErrorText()
    fun showPassedNoDataText()
    fun showSiteNoDataText()
    fun presentPassed(data: List<Passed>)
    fun presentSite(data: List<Site>)
    fun presentPassedLoading()
    fun presentSiteLoading()
}
