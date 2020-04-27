package com.ifeor.welltecemployeeplanner.ui.presenters

import com.ifeor.welltecemployeeplanner.data.repositories.LocationRepositoryImpl
import com.ifeor.welltecemployeeplanner.ui.views.LocationListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LocationListPresenter: MvpPresenter<LocationListView>() {

    private val locationRepository = LocationRepositoryImpl()

    fun fetchLocations() {
        viewState.presentLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val locations = locationRepository.fetchLocationAsync().await()
                withContext(Dispatchers.Main) {
                    viewState.presentLocations(data = locations)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
