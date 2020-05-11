package com.ifeor.welltecemployeeplanner.ui.course.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Course
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_course_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class CourseListFragment : MvpAppCompatFragment(),
    CourseListView {

    @InjectPresenter
    lateinit var courseListPresenter: CourseListPresenter

    private val courseListAdapter =
        CourseListAdapter(::onCourseClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        courseListPresenter.fetchCourses()
        action_add_course.setOnClickListener { (activity as MainActivity).toAddCourse() }
    }

    private fun onCourseClick(course: Course) {
        (context as MainActivity).openCourseScreen(course)
    }

    override fun showLoadErrorText() {
        fragment_course_list_loading.visibility = View.GONE
        fragment_courses_textview_load_error.visibility = View.VISIBLE
    }

    override fun showNoDataText() {
        fragment_course_list_loading.visibility = View.GONE

        fragment_courses_textview_nodata.visibility = View.VISIBLE
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        course_recycler.layoutManager = layoutManager
        course_recycler.adapter = courseListAdapter
    }

    override fun presentCourses(data: List<Course>, userRole: String) {
        fragment_course_list_loading.visibility = View.GONE
        if (userRole == "Guest") {
            course_recycler.visibility = View.GONE
            fragment_courses_textview_guest.visibility = View.VISIBLE
        } else {
            course_recycler.visibility = View.VISIBLE
            fragment_courses_textview_guest.visibility = View.GONE
        }
        setRole(userRole)
        courseListAdapter.updateCourses(newCourses = data)
    }

    override fun setRole(role: String) {
        if (role != "Safety Engineer") {
            action_add_course.visibility = View.GONE
        } else {
            action_add_course.visibility = View.VISIBLE
        }
    }

    override fun presentLoading() {
        action_add_course.visibility = View.GONE
        fragment_course_list_loading.visibility = View.VISIBLE
        course_recycler.visibility = View.GONE
        fragment_courses_textview_guest.visibility = View.GONE
    }
}
