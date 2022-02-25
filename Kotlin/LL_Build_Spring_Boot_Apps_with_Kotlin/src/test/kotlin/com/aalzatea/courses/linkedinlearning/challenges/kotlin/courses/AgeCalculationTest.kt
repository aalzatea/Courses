package com.aalzatea.courses.linkedinlearning.challenges.kotlin.courses

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.util.Calendar
import kotlin.test.assertEquals

internal class AgeCalculationTest {

    private val underTest: AgeCalculation = AgeCalculation()

    @Test
    fun checkAgeWhenBornToday() {
        assertEquals(0, underTest.getAge(Calendar.getInstance()))
    }

    @Test
    fun checkAgeWhenBorn1000DaysAgo() {
        val date = Calendar.getInstance()
        date.add(Calendar.DAY_OF_YEAR, -1000)

        assertEquals(2, underTest.getAge(date))
    }

    @Test
    fun testForException() {
        val date = Calendar.getInstance()
        date.add(Calendar.DAY_OF_YEAR, 10)

        assertThrows(IllegalArgumentException::class.java) {
            underTest.getAge(date)
        }
    }
}