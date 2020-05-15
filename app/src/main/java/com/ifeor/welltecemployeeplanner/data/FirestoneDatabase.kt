package com.ifeor.welltecemployeeplanner.data

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.ifeor.welltecemployeeplanner.data.model.*
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirestoneDatabase {

    val db = FirebaseFirestore.getInstance()

    private val employeeList = ArrayList<Employee>()

    fun addEmployee(
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
                "employeeFirstName" to firstName,
                "employeeSecondName" to secondName,
                "employeePosition" to position,
                "employeeRole" to role,
                "employeeEmail" to email,
                "employeePhoneNumber" to phoneNumber
            )
        )
    }

    fun addGuest(
        firstName: String,
        secondName: String,
        position: String,
        email: String,
        phoneNumber: String
    ) {
        val employeeCollection = db.collection("guest")
        employeeCollection.document(email).set(
            mapOf(
                "employeeFirstName" to firstName,
                "employeeSecondName" to secondName,
                "employeePosition" to position,
                "employeeRole" to "Guest",
                "employeeEmail" to email,
                "employeePhoneNumber" to phoneNumber
            )
        )
    }

    fun addCourse(
        title: String,
        description: String,
        period: Int = 0
    ) {
        val courseCollection = db.collection("course")
        courseCollection.add(
            mapOf(
                "courseTitle" to title,
                "courseDesc" to description,
                "coursePeriod" to period
            )
        )
    }

    fun addNotification(
        title: String,
        description: String,
        publishDate: String
    ) {
        val notificationCollection = db.collection("notification")
        notificationCollection.add(
            mapOf(
                "notificationTitle" to title,
                "notificationDesc" to description,
                "notificationDate" to publishDate
            )
        )
    }

    fun addLocation(
        title: String,
        description: String
    ) {
        val locationCollection = db.collection("location")
        locationCollection.add(
            mapOf(
                "locationTitle" to title,
                "locationDesc" to description
            )
        )
    }

    fun addPassedCourse(
        employeeEmail: String,
        courseTitle: String,
        passedDate: GregorianCalendar
    ) {
        val passedCourseCollection = db.collection("passed")
        passedCourseCollection.add(
            mapOf(
                "employeeEmail" to employeeEmail,
                "courseTitle" to courseTitle,
                "passedDate" to passedDate
            )
        )
    }

    // TODO - Notification ID
    fun addViewedNotification(
        employeeEmail: String,
        notificationId: Long
    ) {
        val viewedNotificationCollection = db.collection("viewed")
        viewedNotificationCollection.add(
            mapOf(
                "employeeID" to employeeEmail,
                "notificationID" to notificationId
            )
        )
    }

    // TODO
    fun addSite(
        employeeEmail: String,
        locationTitle: String
    ) {
        val siteCollection = db.collection("site")
        siteCollection.add(
            mapOf(
                "employeeEmail" to employeeEmail,
                "locationTitle" to locationTitle
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

    suspend fun getEmployee(documentId: String) = suspendCoroutine<Employee> { cont ->
        db.collection("employee")
            .document(documentId)
            .get()
            .addOnSuccessListener {result ->
                cont.resume(result.toObject(Employee::class.java)!!)
            }
    }

    // Collections
    suspend fun getEmployeeList() = suspendCoroutine<List<Employee>> { cont ->
        db.collection("employee")
            .get()
            .addOnSuccessListener { result -> cont.resume(result.toObjects(Employee::class.java)) }
    }

    suspend fun getGuestList() = suspendCoroutine<List<Employee>> { cont ->
        db.collection("guest")
            .get()
            .addOnSuccessListener { result -> cont.resume(result.toObjects(Employee::class.java)) }
    }

    suspend fun getNotificationList() = suspendCoroutine<List<Notification>> { cont ->
        db.collection("notification")
            .get()
            .addOnSuccessListener { result -> cont.resume(result.toObjects(Notification::class.java)) }
    }

    suspend fun getLocationList() = suspendCoroutine<List<Location>> { cont ->
        db.collection("location")
            .get()
            .addOnSuccessListener { result -> cont.resume(result.toObjects(Location::class.java)) }
    }

    suspend fun getCourseList() = suspendCoroutine<List<Course>> { cont ->
        db.collection("course")
            .get()
            .addOnSuccessListener { result -> cont.resume(result.toObjects(Course::class.java)) }
    }

    /* OLD
    fun getCollection() {
        db.collection("notification")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val notification: Notification = document.toObject(Notification::class.java)
                    notificationList.add(notification)
                }
            }
    }*/
}
