package com.ifeor.welltecemployeeplanner.ui.employee.item

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

    fun fetchPassed() {
        viewState.presentPassedLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val passed = passedRepository.fetchPassedAsync().await()
                withContext(Dispatchers.Main) {
                    if (passed.isNotEmpty()) {
                        viewState.presentPassed(data = passed)
                    } else {
                        viewState.showPassedNoDataText()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showPassedLoadErrorText()
            }
        }
    }

    fun fetchSite() {
        viewState.presentSiteLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val site = siteRepository.fetchSiteAsync().await()
                withContext(Dispatchers.Main) {
                    if (site.isNotEmpty()) {
                        viewState.presentSite(data = site)
                    } else {
                        viewState.showSiteNoDataText()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showSiteLoadErrorText()
            }
        }
    }
}
