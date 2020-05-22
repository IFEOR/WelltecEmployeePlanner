package com.ifeor.welltecemployeeplanner.ui.location.item

import com.ifeor.welltecemployeeplanner.data.model.Site
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface LocationView: MvpView {
    fun presentSite(data: List<Site>)
    fun presentSiteLoading()
    fun showSiteNoDataText()
}
