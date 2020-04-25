package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.ui.presenters.EmployeeListPresenter
import com.ifeor.welltecemployeeplanner.ui.views.EmployeeListView
import kotlinx.android.synthetic.main.fragment_employee_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class EmployeeListFragment : MvpAppCompatFragment(), EmployeeListView {

    @InjectPresenter
    lateinit var employeeListPresenter: EmployeeListPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee_list, container, false)
    }

    override fun showNoDataText() {
        fragment_employees_textview_nodata.text = (R.string.fragment_employees_no_data_text).toString()
    }
}
