package com.ifeor.welltecemployeeplanner.ui.activities

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Course
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.data.model.Location
import com.ifeor.welltecemployeeplanner.data.model.Notification
import com.ifeor.welltecemployeeplanner.ui.course.item.CourseFragment
import com.ifeor.welltecemployeeplanner.ui.employee.item.EmployeeFragment
import com.ifeor.welltecemployeeplanner.ui.location.item.LocationFragment
import com.ifeor.welltecemployeeplanner.ui.notification.item.NotificationFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /// Floating button
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        ///

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        // Fragment navigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_profile,
                R.id.nav_notifications,
                R.id.nav_courses,
                R.id.nav_locations,
                R.id.nav_employees,
                R.id.nav_share,
                R.id.nav_signout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    // TODO - fix or delete
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_notification -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.nav_add_notification)
                return true
            }
            R.id.action_add_course -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.nav_add_course)
                return true
            }
            R.id.action_add_location -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.nav_add_location)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    fun toAddNotification() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.nav_add_notification)
    }

    fun toAddLocation() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.nav_add_location)
    }

    fun toAddCourse() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.nav_add_course)
    }

    fun toGuests() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.nav_guests)
    }

    fun openEmployeeScreen(employee: Employee) {
        val bundle = Bundle()
        bundle.putSerializable(EmployeeFragment.EMPLOYEE_DATA_TAG, employee)
        findNavController(R.id.nav_host_fragment).navigate(R.id.nav_employee, bundle)
    }

    fun openNotificationScreen(notification: Notification) {
        val bundle = Bundle()
        bundle.putSerializable(NotificationFragment.NOTIFICATION_DATA_TAG, notification)
        findNavController(R.id.nav_host_fragment).navigate(R.id.nav_notification, bundle)
    }

    fun openCourseScreen(course: Course) {
        val bundle = Bundle()
        bundle.putSerializable(CourseFragment.COURSE_DATA_TAG, course)
        findNavController(R.id.nav_host_fragment).navigate(R.id.nav_course, bundle)
    }

    fun openLocationScreen(location: Location) {
        val bundle = Bundle()
        bundle.putSerializable(LocationFragment.LOCATION_DATA_TAG, location)
        findNavController(R.id.nav_host_fragment).navigate(R.id.nav_location, bundle)
    }
}
