package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import kotlinx.android.synthetic.main.fragment_location_add.*

class LocationAddFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_location_add_button.setOnClickListener {
            val firestoneDB = FirestoneDatabase()
            firestoneDB.addLocation(
                firestoneDB.connectDB(),
                0,
                fragment_location_add_name.toString(),
                fragment_location_add_desc.toString()
            )
        }
    }
}
