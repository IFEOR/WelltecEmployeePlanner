package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Location
import com.ifeor.welltecemployeeplanner.ui.presenters.LocationPresenter
import com.ifeor.welltecemployeeplanner.ui.views.LocationView
import kotlinx.android.synthetic.main.fragment_location.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class LocationFragment: MvpAppCompatFragment(), LocationView {

    companion object {
        const val LOCATION_DATA_TAG = "location"
    }

    @InjectPresenter
    lateinit var locationPresenter: LocationPresenter

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
    }

    private fun setLocationCredentials() {
        val location = arguments?.getSerializable(LOCATION_DATA_TAG) as Location
        fragment_location_title.text = location.locationTitle
        fragment_location_desc.text = location.locationDesc
    }
}
