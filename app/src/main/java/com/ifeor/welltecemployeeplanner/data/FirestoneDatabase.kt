package com.ifeor.welltecemployeeplanner.data

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.ifeor.welltecemployeeplanner.data.model.Course
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.data.model.Location
import com.ifeor.welltecemployeeplanner.data.model.Notification
import java.util.*

class FirestoneDatabase {

    private val employeeList = ArrayList<Employee>()
    private val notificationList = ArrayList<Notification>()
    private val locationList = ArrayList<Location>()
    private val courseList = ArrayList<Course>()

    val db = FirebaseFirestore.getInstance()

    fun addEmployee(
        employeeId: Long,
        firstName: String,
        secondName: String,
        position: String,
        role: String,
        email: String,
        phoneNumber: String
    ) {
        val employeeCollection = db.collection("employee")
        employeeCollection.add(
            mapOf(
                "employeeID" to employeeId,
                "employeeFirstName" to firstName,
                "employeeSecondName" to secondName,
                "employeePosition" to position,
                "employeeRole" to role,
                "employeeEmail" to email,
                "employeePhoneNumber" to phoneNumber
            )
        )
    }

    fun addCourse(
        courseId: Long,
        title: String,
        description: String,
        period: Int = 0
    ) {
        val courseCollection = db.collection("course")
        courseCollection.add(
            mapOf(
                "courseID" to courseId,
                "courseTitle" to title,
                "courseDesc" to description,
                "coursePeriod" to period
            )
        )
    }

    fun addNotification(
        notificationId: Long,
        title: String,
        description: String,
        publishDate: String
    ) {
        val notificationCollection = db.collection("notification")
        notificationCollection.add(
            mapOf(
                "notificationID" to notificationId,
                "notificationTitle" to title,
                "notificationDesc" to description,
                "notificationDate" to publishDate
            )
        )
    }

    fun addLocation(
        locationId: Long,
        title: String,
        description: String
    ) {
        val locationCollection = db.collection("location")
        locationCollection.add(
            mapOf(
                "locationID" to locationId,
                "locationTitle" to title,
                "locationDesc" to description
            )
        )
    }

    // TODO
    fun addPassedCourse(
        employeeId: Long,
        courseId: Long,
        passedDate: GregorianCalendar
    ) {
        val passedCourseCollection = db.collection("passed")
        passedCourseCollection.add(
            mapOf(
                "employeeID" to employeeId,
                "courseID" to courseId,
                "passedDate" to passedDate
            )
        )
    }

    // TODO
    fun addViewedNotification(
        employeeId: Long,
        notificationId: Long
    ) {
        val viewedNotificationCollection = db.collection("viewed")
        viewedNotificationCollection.add(
            mapOf(
                "employeeID" to employeeId,
                "notificationID" to notificationId
            )
        )
    }

    // TODO
    fun addSite(
        employeeId: Long,
        locationId: Long
    ) {
        val siteCollection = db.collection("site")
        siteCollection.add(
            mapOf(
                "employeeID" to employeeId,
                "locationID" to locationId
            )
        )
    }

    // TODO
    fun deleteDocument(
        collectionName: String,
        documentID: String
    ) {
        db.collection(collectionName)
            .document(documentID)
            .delete()
    }

    // TODO
    fun updateDocument(
        collectionName: String,
        documentID: String,
        field: String,
        value: FieldValue
    ) {
        db.collection(collectionName)
            .document(documentID)
            .update(field, value)
    }

    // TODO
    fun getDocument(db: FirebaseFirestore, collectionName: String, documentID: String) {
        db.collection(collectionName)
            .document(documentID)
            .get()
    }

    fun getEmployees() {
        db.collection("employee")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val employee: Employee = document.toObject(Employee::class.java)
                    employeeList.add(employee)
                }
            }
    }

    fun getEmployeeList(): ArrayList<Employee> {
        return employeeList
    }

    fun getNotifications() {
        db.collection("notification")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val notification: Notification = document.toObject(Notification::class.java)
                    notificationList.add(notification)
                }
            }
    }

    fun getNotificationList(): ArrayList<Notification> {
        return notificationList
    }

    fun getLocations() {
        db.collection("location")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val location: Location = document.toObject(Location::class.java)
                    locationList.add(location)
                }
            }
    }

    fun getLocationList(): ArrayList<Location> {
        return locationList
    }

    fun getCourses() {
        db.collection("course")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val course: Course = document.toObject(Course::class.java)
                    courseList.add(course)
                }
            }
    }

    fun getCourseList(): ArrayList<Course> {
        return courseList
    }
}
