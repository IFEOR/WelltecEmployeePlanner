package com.ifeor.welltecemployeeplanner.ui.location.item

import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.data.model.Site
import com.ifeor.welltecemployeeplanner.data.repositories.EmployeeRepositoryImpl
import com.ifeor.welltecemployeeplanner.data.repositories.SiteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LocationPresenter: MvpPresenter<LocationView>() {

    private val employeeRepository = EmployeeRepositoryImpl()
    private val siteRepository = SiteRepositoryImpl()

    fun fetchSite(locationTitle: String) {
        viewState.presentSiteLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val site: MutableList<Site> = siteRepository.fetchSiteAsync().await()
                val employee: List<Employee> = employeeRepository.fetchEmployeesAsync().await()
                withContext(Dispatchers.Main) {
                    for (i in 0..site.size-1) {
                        if (site[i].locationTitle != locationTitle) site.removeAt(i)
                    }
                    for (i in 0..site.size-1) {
                        for (empl in 0..employee.size-1) {
                            if (site[i].employeeEmail == employee[empl].employeeEmail) {
                                val name = "${employee[empl].employeeFirstName} ${employee[empl].employeeSecondName}"
                                site[i].employeeEmail = name
                            }
                        }
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
