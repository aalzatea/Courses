package com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise1

import java.util.GregorianCalendar

fun main() {
    val john = KotlinPerson(1L, "Mr", "John", "Blue", GregorianCalendar(1977,9,3))
    val jane = KotlinPerson(2L, "Mrs", "Jane", "Green", null)
    val robert = KotlinPerson(3L, "Mr", "Robert", "Red", GregorianCalendar(1984, 7, 19))

    println("$john's age is ${john.getAge()}")
    println("$jane's age is ${jane.getAge()}")
    println("$robert's age is ${robert.age1}")
    println("The age of someone born on 3rd May 1988 is " + KotlinPerson.getAge(GregorianCalendar(1988,5,3)))
}