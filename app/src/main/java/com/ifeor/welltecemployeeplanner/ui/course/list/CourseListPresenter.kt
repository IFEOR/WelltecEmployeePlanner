package com.ifeor.welltecemployeeplanner.ui.course.list

import com.ifeor.welltecemployeeplanner.data.repositories.CourseRepositoryImpl
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
                    if (courses.isNotEmpty()) {
                        viewState.presentCourses(data = courses)
                    } else {
                        viewState.showNoDataText()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showLoadErrorText()
            }
        }
    }
}
