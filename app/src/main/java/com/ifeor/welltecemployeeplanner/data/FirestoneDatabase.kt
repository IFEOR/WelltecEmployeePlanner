package com.ifeor.welltecemployeeplanner.data

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ifeor.welltecemployeeplanner.data.model.Course
import com.ifeor.welltecemployeeplanner.data.model.Employee
import com.ifeor.welltecemployeeplanner.data.model.Location
import com.ifeor.welltecemployeeplanner.data.model.Notification
import com.ifeor.welltecemployeeplanner.data.repositories.EmployeeRepositoryImpl
import java.util.*

class FirestoneDatabase {

    private val employeeList = ArrayList<Employee>()
    private val notificationList = ArrayList<Notification>()
    private val locationList = ArrayList<Location>()
    private val courseList = ArrayList<Course>()

    val db = FirebaseFirestore.getInstance()

    fun addEmployee(
        db: FirebaseFirestore,
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
        db: FirebaseFirestore,
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
        db: FirebaseFirestore,
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

    fun addLocation(db: FirebaseFirestore, locationId: Long, title: String, description: String) {
        val locationCollection = db.collection("location")
        locationCollection.add(
            mapOf(
                "locationID" to locationId,
                "locationTitle" to title,
                "locationDesc" to description
            )
        )
    }

    fun addPassedCourse(
        db: FirebaseFirestore,
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

    fun addViewedNotification(db: FirebaseFirestore, employeeId: Long, notificationId: Long) {
        val viewedNotificationCollection = db.collection("viewed")
        viewedNotificationCollection.add(
            mapOf(
                "employeeID" to employeeId,
                "notificationID" to notificationId
            )
        )
    }

    fun addSite(db: FirebaseFirestore, employeeId: Long, locationId: Long) {
        val siteCollection = db.collection("site")
        siteCollection.add(
            mapOf(
                "employeeID" to employeeId,
                "locationID" to locationId
            )
        )
    }

    fun deleteDocument(db: FirebaseFirestore, collectionName: String, documentID: String) {
        db.collection(collectionName)
            .document(documentID)
            .delete()
    }

    fun updateDocument(
        db: FirebaseFirestore,
        collectionName: String,
        documentID: String,
        field: String,
        value: FieldValue
    ) {
        db.collection(collectionName)
            .document(documentID)
            .update(field, value)
    }

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

    fun getNotificationList(): ArrayList<Employee> {
        return notificationList
    }
}
