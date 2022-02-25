package com.aalzatea.courses.linkedinlearning.challenges.kotlin.courses

import java.util.Calendar

class AgeCalculation {

    fun getAge(dateOfBirth: Calendar): Int {
        val today = Calendar.getInstance()
        if (dateOfBirth.timeInMillis > today.timeInMillis) throw IllegalArgumentException()

        val years = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR)

        return if (dateOfBirth.get(Calendar.DAY_OF_YEAR) > today.get(Calendar.DAY_OF_YEAR))
            years - 1
        else
            years
    }
}