package com.ifeor.welltecemployeeplanner.ui.employee.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Passed
import kotlinx.android.synthetic.main.item_passed.view.*
import java.util.*

class ProfileAdapter: RecyclerView.Adapter<ProfileAdapter.EmployeeViewHolder>() {

    private val passed: MutableList<Passed> = LinkedList()

    fun updateCourses(newCourses: List<Passed>) {
        passed.clear()
        passed.addAll(newCourses)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
        EmployeeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_passed, parent, false)
        )

    override fun getItemCount() = passed.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(passed[position])
    }

    class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val passedTitle = view.item_passed_title
        private val passedDate = view.item_passed_date
        fun bind(country: Passed) {
            passedTitle.text = country.courseTitle
            passedDate.text = country.passedDate
        }
    }
}
