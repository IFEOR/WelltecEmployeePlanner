package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.ui.presenters.LocationListPresenter
import com.ifeor.welltecemployeeplanner.ui.views.LocationListView
import kotlinx.android.synthetic.main.fragment_location_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class LocationListFragment : MvpAppCompatFragment(), LocationListView {

    @InjectPresenter
    lateinit var locationListPresenter: LocationListPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_list, container, false)
    }

    override fun showNoDataText() {
        fragment_locations_textview_nodata.text = (R.string.fragment_locations_no_data_text).toString()
    }
}
