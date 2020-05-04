package com.ifeor.welltecemployeeplanner.ui.views

import com.ifeor.welltecemployeeplanner.data.model.Notification
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface NotificationListView : MvpView {

    fun showNoDataText()
    fun showLoadErrorText()
    fun presentNotifications(data: List<Notification>)
    fun presentLoading()
}
