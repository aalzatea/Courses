package com.aalzatea.courses.linkedinlearning.challenges.kotlin.comparator

class Bigger {

    companion object {
        fun <T: Comparable<T>> getBigger(param1: T, param2: T): T {
            val result = param1.compareTo(param2)
            return if (result > 0) param1 else param2
        }
    }
}
