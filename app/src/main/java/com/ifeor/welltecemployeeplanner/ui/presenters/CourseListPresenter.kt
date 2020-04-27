package com.ifeor.welltecemployeeplanner.ui.presenters

import com.ifeor.welltecemployeeplanner.data.repositories.CourseRepositoryImpl
import com.ifeor.welltecemployeeplanner.ui.views.CourseListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CourseListPresenter: MvpPresenter<CourseListView>() {

    private val courseRepository = CourseRepositoryImpl()

    fun fetchCourses() {
        viewState.presentLoading()
        GlobalScope.launch (Dispatchers.IO) {
            try {
                val courses = courseRepository.fetchCourseAsync().await()
                withContext(Dispatchers.Main) {
                    viewState.presentCourses(data = courses)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
