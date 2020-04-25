package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.ui.presenters.CourseListPresenter
import com.ifeor.welltecemployeeplanner.ui.views.CourseListView
import kotlinx.android.synthetic.main.fragment_course_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class CourseListFragment : MvpAppCompatFragment(), CourseListView {

    @InjectPresenter
    lateinit var courseListPresenter: CourseListPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course_list, container, false)
    }

    override fun showNoDataText() {
        fragment_courses_textview_nodata.text = (R.string.fragment_courses_no_data_text).toString()
    }
}
