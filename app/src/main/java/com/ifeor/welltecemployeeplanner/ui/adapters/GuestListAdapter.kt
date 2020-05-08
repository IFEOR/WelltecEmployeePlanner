package com.ifeor.welltecemployeeplanner.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.ui.utils.setImagePlaceholder
import kotlinx.android.synthetic.main.item_employee.view.*
import java.util.*

class GuestListAdapter(private val onClick: (employee: Employee) -> Unit) :
    RecyclerView.Adapter<GuestListAdapter.GuestViewHolder>() {

    private val guests: MutableList<Employee> = LinkedList()

    fun updateGuests(newGuests: List<Employee>) {
        guests.clear()
        guests.addAll(newGuests)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = GuestViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
    )

    override fun getItemCount() = guests.size

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val country = guests[position]
        holder.bind(country, onClick)
    }

    class GuestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val guestImg = view.item_employee_image
        private val guestName = view.item_employee_name

        fun bind(country: Employee, onClick: (employee: Employee) -> Unit) {
            val employee = Employee(
                country.employeeFirstName,
                country.employeeSecondName,
                country.employeePosition,
                country.employeeRole,
                country.employeeEmail,
                country.employeePhoneNumber
            )
            itemView.setOnClickListener { onClick(employee) }
            val name = "${country.employeeFirstName} ${country.employeeSecondName}"
            guestName.text = name
            guestImg.setImagePlaceholder("")
        }
    }
}
