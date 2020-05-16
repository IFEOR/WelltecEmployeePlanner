package com.ifeor.welltecemployeeplanner.ui.location.toEmployee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Location
import kotlinx.android.synthetic.main.item_location.view.*
import java.util.*

class LocationToEmployeeAdapter(private val onClick: (location: Location) -> Unit) :
    RecyclerView.Adapter<LocationToEmployeeAdapter.LocationViewHolder>() {

    private val locations: MutableList<Location> = LinkedList()

    fun updateLocations(newLocations: List<Location>) {
        locations.clear()
        locations.addAll(newLocations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
        )

    override fun getItemCount() = locations.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val country = locations[position]
        holder.bind(country, onClick)
    }

    class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val locationTitle = view.item_location_title
        private val locationDesc = view.item_location_desc

        fun bind(country: Location, onClick: (location: Location) -> Unit) {
            val location = Location(
                country.locationTitle,
                country.locationDesc
            )
            itemView.setOnClickListener { onClick(location) }
            locationTitle.text = country.locationTitle
            locationDesc.text = country.locationDesc
            if (locationDesc.text == "") locationDesc.visibility = View.GONE
        }
    }
}
