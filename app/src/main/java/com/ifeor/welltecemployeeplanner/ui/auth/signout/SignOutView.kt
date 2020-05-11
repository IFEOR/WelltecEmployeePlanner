package com.ifeor.welltecemployeeplanner.ui.auth.signout

import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface SignOutView {

    fun logOut()
}
