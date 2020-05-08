package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import com.ifeor.welltecemployeeplanner.ui.adapters.GuestListAdapter
import com.ifeor.welltecemployeeplanner.ui.presenters.GuestListPresenter
import com.ifeor.welltecemployeeplanner.ui.views.GuestListView
import kotlinx.android.synthetic.main.fragment_guest_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class GuestListFragment : MvpAppCompatFragment(), GuestListView {

    @InjectPresenter
    lateinit var guestListPresenter: GuestListPresenter

    private val guestListAdapter = GuestListAdapter(::onGuestClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_guest_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        guestListPresenter.fetchGuests()
    }

    private fun onGuestClick(employee: Employee) {
        (context as MainActivity).openEmployeeScreen(employee)
    }

    override fun showLoadErrorText() {
        fragment_guest_list_loading.visibility = View.GONE
        fragment_guests_textview_load_error.visibility = View.VISIBLE
    }

    override fun showNoDataText() {
        fragment_guest_list_loading.visibility = View.GONE
        fragment_guests_textview_nodata.visibility = View.VISIBLE
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        guest_recycler.layoutManager = layoutManager
        guest_recycler.adapter = guestListAdapter
    }

    override fun presentGuests(data: List<Employee>) {
        fragment_guest_list_loading.visibility = View.GONE
        guest_recycler.visibility = View.VISIBLE
        guestListAdapter.updateGuests(newGuests = data)
    }

    override fun presentLoading() {
        fragment_guest_list_loading.visibility = View.VISIBLE
        guest_recycler.visibility = View.GONE
    }
}
