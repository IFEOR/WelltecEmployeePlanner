package com.ifeor.welltecemployeeplanner.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Course
import kotlinx.android.synthetic.main.item_course.view.*
import java.util.*

class CourseListAdapter(private val onClick: (course: Course) -> Unit) :
    RecyclerView.Adapter<CourseListAdapter.CourseViewHolder>() {

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
        val country = courses[position]
        holder.bind(country, onClick)
    }

    class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val courseTitle = view.item_course_title
        private val courseDesc = view.item_course_desc

        fun bind(country: Course, onClick: (course: Course) -> Unit) {

            val course = Course(
                country.courseTitle,
                country.courseDesc,
                country.coursePeriod
            )
            itemView.setOnClickListener { onClick(course) }

            courseTitle.text = country.courseTitle
            courseDesc.text = country.courseDesc
            if (courseDesc.text == "") courseDesc.visibility = View.GONE
        }
    }
}
