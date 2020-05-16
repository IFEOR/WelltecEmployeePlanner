package com.ifeor.welltecemployeeplanner.ui.course.toEmployee

import com.ifeor.welltecemployeeplanner.data.model.Course
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface CourseToEmployeeView : MvpView {

    fun showLoadErrorText()
    fun showNoDataText()
    fun presentCourses(data: List<Course>)
    fun presentLoading()
}
