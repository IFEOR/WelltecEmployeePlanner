package com.ifeor.welltecemployeeplanner.ui.employee.toCourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.ui.employee.list.EmployeeListAdapter
import com.ifeor.welltecemployeeplanner.ui.utils.setImagePlaceholder
import kotlinx.android.synthetic.main.item_employee.view.*
import java.util.*

class EmployeeToCourseAdapter(private val onClick: (employee: Employee) -> Unit) :
    RecyclerView.Adapter<EmployeeToCourseAdapter.EmployeeViewHolder>() {

    private val employees: MutableList<Employee> = LinkedList()

    fun updateEmployees(newEmployees: List<Employee>) {
        employees.clear()
        employees.addAll(newEmployees)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        EmployeeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        )

    override fun getItemCount() = employees.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val country = employees[position]
        holder.bind(country, onClick)
    }

    class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val employeeImg = view.item_employee_image
        private val employeeName = view.item_employee_name

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
            employeeName.text = name
            employeeImg.setImagePlaceholder("")
        }
    }
}
