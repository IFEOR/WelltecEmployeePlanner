package com.ifeor.welltecemployeeplanner.ui.employee.profile

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
}
