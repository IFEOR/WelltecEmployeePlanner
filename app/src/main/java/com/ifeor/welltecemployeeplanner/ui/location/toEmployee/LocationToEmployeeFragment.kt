package com.ifeor.welltecemployeeplanner.ui.location.toEmployee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Location
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_choice_location.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class LocationToEmployeeFragment : MvpAppCompatFragment(),
    LocationToEmployeeView {

    @InjectPresenter
    lateinit var locationToEmployeePresenter: LocationToEmployeePresenter

    private val locationToEmployeeAdapter =
        LocationToEmployeeAdapter(::onLocationClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choice_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        locationToEmployeePresenter.fetchLocations()
    }

    private fun onLocationClick(location: Location) {
        (activity as MainActivity).addSiteByLocation(location.locationTitle)
    }

    override fun showLoadErrorText() {
        fragment_choice_location_loading.visibility = View.GONE
        fragment_choice_location_textview_load_error.visibility = View.VISIBLE
    }

    override fun showNoDataText() {
        fragment_choice_location_loading.visibility = View.GONE
        fragment_choice_location_textview_nodata.visibility = View.VISIBLE
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        choice_location_recycler.layoutManager = layoutManager
        choice_location_recycler.adapter = locationToEmployeeAdapter
    }

    override fun presentLocations(data: List<Location>) {
        fragment_choice_location_loading.visibility = View.GONE
        choice_location_recycler.visibility = View.VISIBLE
        locationToEmployeeAdapter.updateLocations(newLocations = data)
    }

    override fun presentLoading() {
        fragment_choice_location_loading.visibility = View.VISIBLE
        choice_location_recycler.visibility = View.GONE
    }
}
