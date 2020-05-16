package com.ifeor.welltecemployeeplanner.ui.course.toEmployee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Course
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_choice_course.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class CourseToEmployeeFragment : MvpAppCompatFragment(),
    CourseToEmployeeView {

    @InjectPresenter
    lateinit var courseToEmployeePresenter: CourseToEmployeePresenter

    private val courseToEmployeeAdapter =
        CourseToEmployeeAdapter(::onCourseClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choice_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        courseToEmployeePresenter.fetchCourses()
    }

    private fun onCourseClick(course: Course) {
        if (isValidFields()) {
            (context as MainActivity).addPassedByCourse(
                course.courseTitle,
                fragment_choice_course_passed_date.text.toString()
            )
        }
    }

    override fun showLoadErrorText() {
        fragment_choice_course_loading.visibility = View.GONE
        fragment_choice_course_textview_load_error.visibility = View.VISIBLE
    }

    override fun showNoDataText() {
        fragment_choice_course_loading.visibility = View.GONE
        fragment_choice_course_textview_nodata.visibility = View.VISIBLE
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        choice_course_recycler.layoutManager = layoutManager
        choice_course_recycler.adapter = courseToEmployeeAdapter
    }

    override fun presentCourses(data: List<Course>) {
        fragment_choice_course_loading.visibility = View.GONE
        choice_course_recycler.visibility = View.VISIBLE
        courseToEmployeeAdapter.updateCourses(newCourses = data)
    }

    override fun presentLoading() {
        fragment_choice_course_loading.visibility = View.VISIBLE
        choice_course_recycler.visibility = View.GONE
    }

    private fun isValidFields(): Boolean {
        var isNotError = true
        if (fragment_choice_course_passed_date.text.isEmpty()) {
            fragment_choice_course_passed_date.error = getString(R.string.error_empty)
            isNotError = false
        }
        return isNotError
    }
}
