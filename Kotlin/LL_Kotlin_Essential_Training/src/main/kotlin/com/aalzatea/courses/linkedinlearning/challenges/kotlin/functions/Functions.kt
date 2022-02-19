package com.aalzatea.courses.linkedinlearning.challenges.kotlin.functions

fun replicate(times: Int, message: String = "* Be Cool.", func: (Int, String) -> Unit) {
    func(times, message)
}

fun main() {
    val func: (Int, String) -> Unit = { times: Int, message: String -> for (index in 1..times) println(message) }
    replicate(3, func=func)
    replicate (5, "-> Test") { times, message ->
        for (index in 1..times) println(message)
    }
}