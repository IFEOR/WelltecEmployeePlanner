package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.model.Employee
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EmployeeRepositoryImpl {

    fun fetchEmployeesAsync(): Deferred<List<Employee>> {
        val mockData = ArrayList<Employee>()

        mockData.add(Employee(0, "Sergey", "Laptiyov", "davecl@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(1, "Zhanaim", "Zhaksylyk", "marinemillowich@welltec.com", "", listOf("+7(777)888-88-88")))
        mockData.add(Employee(2, "Andrey", "Fatkhutdinov", "davecl@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(3, "Dmitriy", "Gabzagirov", "macaruni@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(4, "Amina", "Shynybayeva", "davecl@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(5, "Sergey", "Zaramenskikh", "marinemillowich@welltec.com", "", listOf("+7(777)888-88-88")))
        mockData.add(Employee(6, "Igor", "Ksenzenko", "macaruni@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(7, "Yegor", "Filatov", "davecl@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(8, "Donald", "Campbell", "davecl@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))

        return GlobalScope.async { mockData  }
    }
}
