package com.ifeor.welltecemployeeplanner.ui.course.item

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface CourseView: MvpView {

}
