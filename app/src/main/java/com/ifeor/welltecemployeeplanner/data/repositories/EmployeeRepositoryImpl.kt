package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.FirestoneDatabase
import com.ifeor.welltecemployeeplanner.data.model.Employee
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EmployeeRepositoryImpl {

    fun fetchEmployeesAsync(): Deferred<List<Employee>> {

        //val db = FirestoneDatabase()
        //val data = db.getCollection(db.connectDB(), "employee")

        val mockData = ArrayList<Employee>()

        mockData.add(Employee(0, "Sergey", "Laptiyov", "Engineer", "User","davecl@welltec.com", "+7(777)888-88-88"))
        mockData.add(Employee(1, "Zhanaim", "Zhaksylyk", "Engineer", "User","davecl@welltec.com", "+7(777)888-88-88"))
        mockData.add(Employee(2, "Andrey", "Fatkhutdinov", "Engineer", "User","davecl@welltec.com", "+7(777)888-88-88"))
        mockData.add(Employee(3, "Dmitriy", "Gabzagirov", "Engineer", "User","davecl@welltec.com", "+7(777)888-88-88"))
        mockData.add(Employee(4, "Amina", "Shynybayeva", "Engineer", "User","davecl@welltec.com", "+7(777)888-88-88"))
        mockData.add(Employee(5, "Sergey", "Zaramenskikh", "Engineer", "User","davecl@welltec.com", "+7(777)888-88-88"))
        mockData.add(Employee(6, "Igor", "Ksenzenko", "Engineer", "User","davecl@welltec.com", "+7(777)888-88-88"))
        mockData.add(Employee(7, "Yegor", "Filatov", "Engineer", "User","davecl@welltec.com", "+7(777)888-88-88"))
        mockData.add(Employee(8, "Donald", "Campbell", "Engineer", "User","davecl@welltec.com", "+7(777)888-88-88"))

        return GlobalScope.async { mockData  }
    }
}
