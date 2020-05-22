package com.ifeor.welltecemployeeplanner.ui.location.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Location
import com.ifeor.welltecemployeeplanner.data.model.Site
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_location.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class LocationFragment: MvpAppCompatFragment(),
    LocationView {

    companion object {
        const val LOCATION_DATA_TAG = "location"
    }

    @InjectPresenter
    lateinit var locationPresenter: LocationPresenter

    private val locationAdapter = LocationAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLocationCredentials()
        val location = arguments?.getSerializable(LOCATION_DATA_TAG) as Location
        setupAdapter()
        locationPresenter.fetchSite(location.locationTitle)
        fragment_location_add_btn.setOnClickListener { setAddButtonClick() }
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        location_employee_recycler.layoutManager = layoutManager
        location_employee_recycler.adapter = locationAdapter
    }

    private fun setLocationCredentials() {
        val location = arguments?.getSerializable(LOCATION_DATA_TAG) as Location
        fragment_location_title.text = location.locationTitle
        fragment_location_desc.text = location.locationDesc
    }

    private fun setAddButtonClick() {
        (activity as MainActivity).toChoiceEmployeeToLocation()
    }

    override fun presentSite(data: List<Site>) {
        fragment_location_employees.visibility = View.GONE
        locationAdapter.updateEmployees( newEmployees = data)
    }

    override fun presentSiteLoading() {
        fragment_location_employees.text = getString(R.string.txt_loading)
    }

    override fun showSiteNoDataText() {
        fragment_location_employees.text = getString(R.string.no_employees)
    }
}
