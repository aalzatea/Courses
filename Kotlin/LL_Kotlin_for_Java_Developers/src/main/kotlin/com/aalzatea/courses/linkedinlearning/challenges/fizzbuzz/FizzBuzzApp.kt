package com.aalzatea.courses.linkedinlearning.challenges.fizzbuzz

fun main() {
    println("Fizz Buzz")

    // Create a loop to display the numbers 1 to 105
    // after the number
    //  print "fizz" if the number is divisible by 3
    //  print "buzz" if the number is divisible by 5
    //  print "fizz buzz" if the number is divisible by 3 and 5

    // Add your solution here
    for (number in 1..105) {
        when {
            number % 15 == 0 -> println("Number $number is fizz buzz")
            number % 3 == 0 -> println("Number $number is fizz")
            number % 5 == 0 -> println("Number $number is buzz")
            else -> println("Number $number is nothing")
        }
    }
}