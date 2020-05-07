package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.ui.presenters.EmployeePresenter
import com.ifeor.welltecemployeeplanner.ui.views.EmployeeView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class EmployeeFragment: MvpAppCompatFragment(), EmployeeView {

    @InjectPresenter
    lateinit var employeePresenter: EmployeePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }
}
