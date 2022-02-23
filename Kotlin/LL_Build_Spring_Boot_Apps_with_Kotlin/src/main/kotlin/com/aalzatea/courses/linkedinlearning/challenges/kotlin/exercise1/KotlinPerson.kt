package com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise1

import java.util.Calendar
import java.util.GregorianCalendar

data class KotlinPerson (private val id: Long,
                    private val title: String,
                    private val firstName: String,
                    private val surname: String,
                    private val dateOfBirth: Calendar?) {

    companion object {
        fun getAge(dateOfBirth: Calendar?): Int {
            if (dateOfBirth == null) return -1

            val today = GregorianCalendar()
            val years: Int = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR)

            return if (dateOfBirth.get(Calendar.DAY_OF_YEAR) >= today.get(Calendar.YEAR)) years - 1 else years
        }
    }

    val age1: Int
        get() = getAge(dateOfBirth)

    fun getAge(): Int = Companion.getAge(dateOfBirth)

    override fun toString(): String = "$title $firstName $surname"
}