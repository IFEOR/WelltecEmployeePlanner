package com.ifeor.welltecemployeeplanner.ui.employee.profile

import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {

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
