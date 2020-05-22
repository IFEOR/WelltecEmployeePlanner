package com.ifeor.welltecemployeeplanner.ui.employee.item

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Employee
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
class EmployeePresenter: MvpPresenter<EmployeeView>() {

    private val passedRepository = PassedRepositoryImpl()
    private val siteRepository = SiteRepositoryImpl()

    fun fetchPassed(employeeEmail: String) {
        viewState.presentPassedLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val passed = passedRepository.fetchPassedAsync().await()
                withContext(Dispatchers.Main) {
                    val size = passed.size - 1
                    for (i in 0..size) {
                        if (passed[i].employeeEmail != employeeEmail) {
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

    fun fetchSite(employeeEmail: String) {
        viewState.presentSiteLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val site: MutableList<Site> = siteRepository.fetchSiteAsync().await()
                withContext(Dispatchers.Main) {
                    for (i in 0..site.size-1) {
                        if (site[i].employeeEmail != employeeEmail) site.removeAt(i)
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
}
