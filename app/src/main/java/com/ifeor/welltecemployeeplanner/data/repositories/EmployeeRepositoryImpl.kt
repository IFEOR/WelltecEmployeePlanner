package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.model.Employee
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EmployeeRepositoryImpl {

    suspend fun fetchEmployees(): Deferred<List<Employee>> {
        val mockData = ArrayList<Employee>()

        mockData.add(Employee(0, "Dave", "Clinton", "davecl@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(1, "Marine", "Millowich", "marinemillowich@welltec.com", "", listOf("+7(777)888-88-88")))
        mockData.add(Employee(2, "Dave", "Clinton", "davecl@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(3, "Macaruni", "Esspiraldo", "macaruni@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(4, "Dave", "Clinton", "davecl@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(5, "Marine", "Millowich", "marinemillowich@welltec.com", "", listOf("+7(777)888-88-88")))
        mockData.add(Employee(6, "Macaruni", "Esspiraldo", "macaruni@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))
        mockData.add(Employee(7, "Dave", "Clinton", "davecl@welltec.com", "", listOf("+7(777)888-88-88", "+7(777)888-88-88")))

        return GlobalScope.async { mockData  }
    }
}
