package com.ifeor.welltecemployeeplanner.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Course
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
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

        action_add_course.setOnClickListener { (activity as MainActivity).toAddCourse() }
        setRole()
    }

    fun setRole() {

        val user = FirebaseAuth.getInstance().currentUser
        val user_email: String = user!!.email + ""
        Log.d("User email: ", user_email)

        val db = FirestoneDatabase()
        db.getEmployeeDocument(user_email)
        Thread.sleep(2000)
        val userRole = db.getEmployeeList()[0].employeeRole

        if(userRole != "Safety Engineer") {
            action_add_course.visibility = View.GONE
        } else {
            action_add_course.visibility = View.VISIBLE
        }
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
