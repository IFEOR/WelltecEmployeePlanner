package com.ifeor.welltecemployeeplanner.ui.employee.profile

import com.ifeor.welltecemployeeplanner.data.model.Passed
import com.ifeor.welltecemployeeplanner.data.model.Site
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface ProfileView: MvpView {
    fun presentEmployee(
        firstName: String,
        secondName: String,
        position: String,
        phone: String,
        email: String,
        role: String)
    fun presentLoading()
    fun showPassedLoadErrorText()
    fun showSiteLoadErrorText()
    fun showPassedNoDataText()
    fun showSiteNoDataText()
    fun presentPassed(data: List<Passed>)
    fun presentSite(data: List<Site>)
    fun presentPassedLoading()
    fun presentSiteLoading()
}
