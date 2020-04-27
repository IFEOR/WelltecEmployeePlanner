package com.ifeor.welltecemployeeplanner.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Location
import kotlinx.android.synthetic.main.item_location.view.*
import java.util.*

class LocationListAdapter : RecyclerView.Adapter<LocationListAdapter.LocationViewHolder>() {

    private val locations: MutableList<Location> = LinkedList()

    fun updateLocations(newLocations: List<Location>) {
        locations.clear()
        locations.addAll(newLocations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = LocationViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
    )

    override fun getItemCount() = locations.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locations[position])
    }

    class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val locationTitle = view.item_location_title
        private val locationDesc = view.item_location_desc

        fun bind(country: Location) {
            locationTitle.text = country.locationTitle
            locationDesc.text = country.locationDesc
        }
    }
}
