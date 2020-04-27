package com.ifeor.welltecemployeeplanner.data.repositories

import com.ifeor.welltecemployeeplanner.data.model.Course
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class CourseRepositoryImpl {

    fun fetchCourseAsync(): Deferred<List<Course>> {
        val mockData = ArrayList<Course>()

        mockData.add(Course(0, "Industrial safety", "Промышленная безопасность", 3))
        mockData.add(Course(1, "Labour safety","Охрана труда", 3))
        mockData.add(Course(2, "Responsible for keeping the lifting cranes in the good condition","Ответственный за содержание грузоподъемных кранов в исправном состоянии", 3))
        mockData.add(Course(3, "Adhering to industrial safety standards while working with vessel under the pressure","Правила обеспечения промышленной безопасности при эксплуатации сосудов, работающих под давлением", 3))
        mockData.add(Course(4, "Responsible for safe lifting operations with the cranes","Ответственный за безопасное производство работ грузоподъемными кранами", 1))
        mockData.add(Course(5, "Blow out prevention","Управление скважиной при ГНВП", 3))
        mockData.add(Course(6, "Safety while working with hazardous substances","Безопасность при работе с едкими и ядовитыми веществами", 3))
        mockData.add(Course(7, "Fire safety","ПТМ", 3))
        mockData.add(Course(8, "First aid","Первая мeдицинская помощь ", 3))
        mockData.add(Course(9, "Electrical safety up to 1000 V","Электроустановки до 1000 В, 3 группа", 1))
        mockData.add(Course(10, "H2S for PTW KPO","H2S KPO", 3))
        mockData.add(Course(11, "Working at heght","Работы на высоте", 2))
        mockData.add(Course(12, "Risk assessment for PTW","Оценка риска для PTW", 2))
        mockData.add(Course(13, "PTW","Наряд допуск ", 2))
        mockData.add(Course(14, "Induction for PTW","Ознакомление для наряд допуска", 0))
        mockData.add(Course(15, "H2S safety and first aid when somebody inhales H2S","Обучение и проверка знаний по мерам безопасности предупреждения отравления сероводородом, вредными веществами и оказанию первой доврачебной помощи пострадавшим при отравлении", 1))
        mockData.add(Course(16, "Slinger","Стропальщик", 0))
        mockData.add(Course(17, "Operator of the pallet truck","Оператор крана управляемого с пола", 0))
        mockData.add(Course(18, "Responsible for supervision of safe crane operations","Ответственный по надзору за безопасной эксплуатацией грузоподъемных кранов", 0))
        mockData.add(Course(19, "Volunteerily firefighter","О прохождении первоначальной подготовки добровольного пожарного", 0))
        mockData.add(Course(20, "Work in gas hazardous area","Промышленная безопасность при выполнении газоопасных работ ", 0))
        mockData.add(Course(21, "Akbarys H2S training","", 0))
        mockData.add(Course(22, "Forklift operator","Оператор вилочного погрузчика", 0))
        mockData.add(Course(23, "Basic H2S training OPITO Approved","", 0))
        mockData.add(Course(24, "BOSIET","", 0))

        return GlobalScope.async { mockData  }
    }
}
