package com.ifeor.welltecemployeeplanner.ui.location.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Location
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_location_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class LocationListFragment : MvpAppCompatFragment(),
    LocationListView {

    @InjectPresenter
    lateinit var locationListPresenter: LocationListPresenter

    private val locationListAdapter =
        LocationListAdapter(::onLocationClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        locationListPresenter.fetchLocations()
        action_add_location.setOnClickListener { (activity as MainActivity).toAddLocation() }
        setRole()
    }

    private fun setRole() {
        val user = FirebaseAuth.getInstance().currentUser
        val userEmail: String = user!!.email + ""
        val db = FirestoneDatabase()
        var userRole = ""
        GlobalScope.launch {
            userRole = withContext(Dispatchers.IO) {
                db.getEmployee(userEmail).employeeRole
            }
        }
        if (userRole != "Coordinator") {
            action_add_location.visibility = View.GONE
        } else {
            action_add_location.visibility = View.VISIBLE
        }
    }

    private fun onLocationClick(location: Location) {
        (context as MainActivity).openLocationScreen(location)
    }

    override fun showLoadErrorText() {
        fragment_location_list_loading.visibility = View.GONE
        fragment_locations_textview_load_error.visibility = View.VISIBLE
    }

    override fun showNoDataText() {
        fragment_location_list_loading.visibility = View.GONE
        fragment_locations_textview_nodata.visibility = View.VISIBLE
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        location_recycler.layoutManager = layoutManager
        location_recycler.adapter = locationListAdapter
    }

    override fun presentLocations(data: List<Location>) {
        fragment_location_list_loading.visibility = View.GONE
        location_recycler.visibility = View.VISIBLE
        locationListAdapter.updateLocations(newLocations = data)
    }

    override fun presentLoading() {
        fragment_location_list_loading.visibility = View.VISIBLE
        location_recycler.visibility = View.GONE
    }
}
