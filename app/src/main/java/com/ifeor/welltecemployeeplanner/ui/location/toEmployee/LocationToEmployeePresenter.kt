package com.ifeor.welltecemployeeplanner.ui.location.toEmployee

import com.ifeor.welltecemployeeplanner.data.repositories.LocationRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LocationToEmployeePresenter: MvpPresenter<LocationToEmployeeView>() {

    private val locationRepository = LocationRepositoryImpl()

    fun fetchLocations() {
        viewState.presentLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val locations = locationRepository.fetchLocationAsync().await()
                withContext(Dispatchers.Main) {
                    if (locations.isNotEmpty()) {
                        viewState.presentLocations(data = locations)
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
