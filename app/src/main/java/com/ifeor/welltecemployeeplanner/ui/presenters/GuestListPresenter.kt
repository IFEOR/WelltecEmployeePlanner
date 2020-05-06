package com.ifeor.welltecemployeeplanner.ui.presenters

import com.ifeor.welltecemployeeplanner.data.repositories.GuestListRepositoryImpl
import com.ifeor.welltecemployeeplanner.ui.views.GuestListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class GuestListPresenter: MvpPresenter<GuestListView>() {

    private val guestRepository = GuestListRepositoryImpl()

    fun fetchGuests() {
        viewState.presentLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val guests = guestRepository.fetchGuestsAsync().await()
                withContext(Dispatchers.Main) {
                    if (guests.isNotEmpty()) {
                        viewState.presentGuests(data = guests)
                    } else {
                        viewState.showNoDataText()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showLoadErrorText()
            }
        }
    }
}

