package com.ifeor.welltecemployeeplanner.ui.employee.profile

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Site
import com.ifeor.welltecemployeeplanner.data.repositories.PassedRepositoryImpl
import com.ifeor.welltecemployeeplanner.data.repositories.SiteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {

    private val passedRepository = PassedRepositoryImpl()
    private val siteRepository = SiteRepositoryImpl()

    fun fetchPassed() {
        viewState.presentPassedLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val user = FirebaseAuth.getInstance().currentUser
                val userEmail: String = user!!.email + ""
                val passed = passedRepository.fetchPassedAsync().await()
                withContext(Dispatchers.Main) {
                    val size = passed.size - 1
                    for (i in 0..size) {
                        if (passed[i].employeeEmail != userEmail) {
                            passed.removeAt(index = i)
                            Log.d("crash", "removed")
                        }
                    }
                    if (passed.isNotEmpty()) {
                        viewState.presentPassed(data = passed)
                    } else {
                        viewState.showPassedNoDataText()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("crash", "7")
            }
        }
    }

    fun fetchSite() {
        viewState.presentSiteLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val user = FirebaseAuth.getInstance().currentUser
                val userEmail: String = user!!.email + ""
                val site: MutableList<Site> = siteRepository.fetchSiteAsync().await()
                withContext(Dispatchers.Main) {
                    for (i in 0..site.size) {
                        if (site[i].employeeEmail != userEmail) site.removeAt(i)
                    }
                    if (site.isNotEmpty()) {
                        viewState.presentSite(data = site)
                    } else {
                        viewState.showSiteNoDataText()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchEmployee() {
        viewState.presentLoading()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val user = FirebaseAuth.getInstance().currentUser
                val userEmail: String = user!!.email + ""
                val db = FirestoneDatabase()
                val employee = db.getEmployee(userEmail)
                withContext(Dispatchers.Main) {
                    viewState.presentEmployee(
                        firstName = employee.employeeFirstName,
                        secondName = employee.employeeSecondName,
                        position = employee.employeePosition,
                        phone = employee.employeePhoneNumber,
                        email = userEmail,
                        role = employee.employeeRole
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
