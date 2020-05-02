package com.ifeor.welltecemployeeplanner.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.ui.utils.setImagePlaceholder
import kotlinx.android.synthetic.main.item_employee.view.*
import java.util.*

class EmployeeListAdapter : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>() {

    private val employees: MutableList<Employee> = LinkedList()

    fun updateEmployees(newEmployees: List<Employee>) {
        employees.clear()
        employees.addAll(newEmployees)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = EmployeeViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
    )

    override fun getItemCount() = employees.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employees[position])
    }

    class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val employeeImg = view.item_employee_image
        private val employeeName = view.item_employee_name

        @SuppressLint("SetTextI18n")
        fun bind(country: Employee) {
            employeeName.text = country.employeeFirstName + " " + country.employeeSecondName
//            employeeImg.setImagePlaceholder(country.employeeImg)
        }
    }
}
