package com.ifeor.welltecemployeeplanner.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Course
import kotlinx.android.synthetic.main.item_course.view.*
import java.util.*

class CourseListAdapter : RecyclerView.Adapter<CourseListAdapter.CourseViewHolder>() {

    private val courses: MutableList<Course> = LinkedList()

    fun updateCourses(newCourses: List<Course>) {
        courses.clear()
        courses.addAll(newCourses)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = CourseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        )

    override fun getItemCount() = courses.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val courseTitle = view.item_course_title
        private val courseDesc = view.item_course_desc

        fun bind(country: Course) {
            courseTitle.text = country.courseTitle
            courseDesc.text = country.courseDesc
        }
    }
}
