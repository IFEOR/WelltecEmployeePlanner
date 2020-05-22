package com.ifeor.welltecemployeeplanner.ui.location.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Site
import kotlinx.android.synthetic.main.item_employee_simple.view.*
import java.util.*

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private val site: MutableList<Site> = LinkedList()

    fun updateEmployees(newEmployees: List<Site>) {
        site.clear()
        site.addAll(newEmployees)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_employee_simple, parent, false)
        )

    override fun getItemCount() = site.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(site[position])
    }

    class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val employeeName = view.item_employee_simple_name
        fun bind(country: Site) {
            employeeName.text = country.employeeEmail
        }
    }
}
