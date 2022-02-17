package com.aalzatea.courses.linkedinlearning.challenges.kotlin.students

fun main() {
    val students = getStudents()
    val combos = students.map { student -> student.name + " : " + student.age }
    println("Combos: $combos")

    println("The oldest student : ${students.maxByOrNull { it.age }}")

    val studentsWithLongNames = students.filter{ it.name.length > 5 }
    println("Long names: $studentsWithLongNames")

    println("The student with the shortest name : ${students.minByOrNull { it.name.length }}")

    //Add your solution here
    //val shortName = students.minByOrNull { it.name.length }?.name?.length ?: 0
    val shortName = students.minByOrNull { it.name.length }!!.name.length
    val studentWithShortNames = students.filter { it.name.length == shortName }
    println("Short names: $studentWithShortNames")
}