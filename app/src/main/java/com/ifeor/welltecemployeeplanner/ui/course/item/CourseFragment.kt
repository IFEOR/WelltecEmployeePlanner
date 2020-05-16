package com.ifeor.welltecemployeeplanner.ui.course.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Course
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_course.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class CourseFragment: MvpAppCompatFragment(),
    CourseView {

    companion object {
        const val COURSE_DATA_TAG = "course"
    }

    @InjectPresenter
    lateinit var coursePresenter: CoursePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCourseCredentials()
        fragment_course_add_btn.setOnClickListener { setAddButtonClick() }
    }

    private fun setCourseCredentials() {
        val course = arguments?.getSerializable(COURSE_DATA_TAG) as Course
        fragment_course_title.text = course.courseTitle
        fragment_course_desc.text = course.courseDesc
        if(course.coursePeriod > 0) {
            val period = "${course.coursePeriod} year(-s)"
            fragment_course_period.text = period
        }
    }

    fun setAddButtonClick() {
        (activity as MainActivity).toChoiceEmployeeToCourse()
    }
}
