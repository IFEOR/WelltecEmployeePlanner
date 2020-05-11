package com.ifeor.welltecemployeeplanner.ui.course.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Course
import com.ifeor.welltecemployeeplanner.ui.activities.MainActivity
import kotlinx.android.synthetic.main.fragment_course_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        setRole()
    }

    private fun onCourseClick(course: Course) {
        (context as MainActivity).openCourseScreen(course)
    }

    private fun setRole() {
        val user = FirebaseAuth.getInstance().currentUser
        val userEmail: String = user!!.email + ""
        val db = FirestoneDatabase()
        var userRole = ""
        GlobalScope.launch {
            userRole = withContext(Dispatchers.IO) {
                db.getEmployee(userEmail).employeeRole
            }
        }
        if (userRole != "Safety Engineer") {
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
