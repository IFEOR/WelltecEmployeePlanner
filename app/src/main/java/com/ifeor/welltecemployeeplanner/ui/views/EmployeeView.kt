package com.ifeor.welltecemployeeplanner.ui.views

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface EmployeeView: MvpView {

}
