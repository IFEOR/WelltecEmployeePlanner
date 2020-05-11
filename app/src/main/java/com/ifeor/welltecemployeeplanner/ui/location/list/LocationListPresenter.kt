package com.ifeor.welltecemployeeplanner.ui.location.list

import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.repositories.LocationRepositoryImpl
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
                val user = FirebaseAuth.getInstance().currentUser
                val userEmail: String = user!!.email + ""
                val db = FirestoneDatabase()
                val employee = db.getEmployee(userEmail)
                val locations = locationRepository.fetchLocationAsync().await()
                withContext(Dispatchers.Main) {
                    if (locations.isNotEmpty()) {
                        viewState.presentLocations(data = locations, userRole = employee.employeeRole)
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
