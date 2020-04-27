package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Course
import com.ifeor.welltecemployeeplanner.ui.adapters.CourseListAdapter
import com.ifeor.welltecemployeeplanner.ui.presenters.CourseListPresenter
import com.ifeor.welltecemployeeplanner.ui.views.CourseListView
import kotlinx.android.synthetic.main.fragment_course_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class CourseListFragment : MvpAppCompatFragment(), CourseListView {

    @InjectPresenter
    lateinit var courseListPresenter: CourseListPresenter

    private val courseListAdapter = CourseListAdapter()

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
    }

    override fun showNoDataText() {
        fragment_courses_textview_nodata.text = (R.string.fragment_courses_no_data_text).toString()
    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context)
        course_recycler.layoutManager = layoutManager
        course_recycler.adapter = courseListAdapter
    }

    override fun presentCourses(data: List<Course>) {
        fragment_course_list_loading.visibility = View.GONE
        course_recycler.visibility = View.VISIBLE
        courseListAdapter.updateCourses(newCourses = data)
    }

    override fun presentLoading() {
        fragment_course_list_loading.visibility = View.VISIBLE
        course_recycler.visibility = View.GONE
    }
}
