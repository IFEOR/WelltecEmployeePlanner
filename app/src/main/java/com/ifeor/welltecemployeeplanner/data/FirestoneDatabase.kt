package com.ifeor.welltecemployeeplanner.data

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ifeor.welltecemployeeplanner.data.model.Employee
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirestoneDatabase {

    fun connectDB(): FirebaseFirestore {
        return Firebase.firestore
    }

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

    suspend fun getCollection(db: FirebaseFirestore, collectionName: String) =
        suspendCoroutine<List<Employee>> { cont ->
            db.collection(collectionName)
                .get()
                .addOnSuccessListener { result ->
                    cont.resume(result.toObjects(Employee::class.java))
                }
        }
}
