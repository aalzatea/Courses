package com.aalzatea.courses.linkedinlearning.challenges.kotlin.students

data class Student(val name: String, val age: Short)

fun getStudents(): List<Student> {
    return listOf(
        Student("Ginger", 19),
        Student("Michael", 23),
        Student("Maria", 20),
        Student("Joe", 39),
        Student("Bob", 16)
    )
}