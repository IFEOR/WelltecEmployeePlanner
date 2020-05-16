package com.ifeor.welltecemployeeplanner.ui.location.toEmployee

import com.ifeor.welltecemployeeplanner.data.model.Location
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface LocationToEmployeeView : MvpView {

    fun showLoadErrorText()
    fun showNoDataText()
    fun presentLocations(data: List<Location>)
    fun presentLoading()
}
