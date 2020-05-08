package com.ifeor.welltecemployeeplanner.ui.activities

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.auth.FirebaseAuth
import com.ifeor.welltecemployeeplanner.R
import com.ifeor.welltecemployeeplanner.data.model.Course
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.data.model.Location
import com.ifeor.welltecemployeeplanner.data.model.Notification
import com.ifeor.welltecemployeeplanner.ui.fragments.CourseFragment
import com.ifeor.welltecemployeeplanner.ui.fragments.EmployeeFragment
import com.ifeor.welltecemployeeplanner.ui.fragments.LocationFragment
import com.ifeor.welltecemployeeplanner.ui.fragments.NotificationFragment
import kotlinx.android.synthetic.main.fragment_notification_list.*
import kotlinx.android.synthetic.main.nav_header_main.*

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
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_notifications, R.id.nav_courses, R.id.nav_locations,
                R.id.nav_employees,
                R.id.nav_share,
                R.id.nav_signout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // TODO-open profile
        // nav_header_user_image.setOnClickListener { toProfile() }
        // val user = FirebaseAuth.getInstance().currentUser
        // val userEmail: String? = user?.email
        // Log.d("User email: ", userEmail + " or null")
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

    // TODO - open profile
    private fun toProfile() {

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
